package com.enigma.traveloca.service.impl;


import com.enigma.traveloca.dto.request.create.CreateAdminRequest;
import com.enigma.traveloca.dto.request.update.UpdateAdminRequest;
import com.enigma.traveloca.dto.response.AdminResponse;
import com.enigma.traveloca.entity.Admin;
import com.enigma.traveloca.entity.UserCredential;
import com.enigma.traveloca.repository.AdminRepository;
import com.enigma.traveloca.repository.UserCredentialRepository;
import com.enigma.traveloca.service.AdminService;
import com.enigma.traveloca.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
    private final AdminRepository repository;
    private final UserCredentialRepository userCredentialRepository;
    private final ValidationUtil validationUtil;

    @Transactional(readOnly = true)
    @Override
    public List<AdminResponse> findAll() {
        return repository.findAll().stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdminResponse save(CreateAdminRequest request){
        validationUtil.validate(request);
        UserCredential userCredential = userCredentialRepository.findById(request.getUserCredentialId());

        Admin admin = Admin.builder()
                .name(request.getName())
                .userCredential(userCredential)
                .build();
        repository.save(admin);
        return mapToResponse(admin);
    }

    @Transactional(readOnly = true)
    @Override
    public AdminResponse findById(String id){
        Admin admin = repository.findById(id);

        return mapToResponse(admin);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Admin admin = repository.findById(id);

        repository.delete(admin);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public AdminResponse update(UpdateAdminRequest request) {
        Admin admin = repository.findById(request.getId());

        Admin updated = Admin.builder()
                .id(admin.getId())
                .name(request.getName())
                .userCredential(admin.getUserCredential())
                .build();

        return mapToResponse(repository.update(updated));
    }
    private AdminResponse mapToResponse(Admin admin) {
        String userCredentialId;
        if (admin.getUserCredential() == null)  userCredentialId = null;
        else userCredentialId = admin.getUserCredential().getId();

        return AdminResponse.builder()
                .name(admin.getName())
                .id(admin.getId())
                .userCredentialId(userCredentialId)
                .build();
    }
}

