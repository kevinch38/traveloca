package com.enigma.traveloca.service.impl;


import com.enigma.traveloca.dto.request.CreateAdminRequest;
import com.enigma.traveloca.dto.response.AdminResponse;
import com.enigma.traveloca.entity.Admin;
import com.enigma.traveloca.entity.UserCredential;
import com.enigma.traveloca.repository.AdminRepository;
import com.enigma.traveloca.repository.UserCredentialRepository;
import com.enigma.traveloca.service.AdminService;
import com.enigma.traveloca.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
        UserCredential userCredential = userCredentialRepository.findById(request.getUserCredentialId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User credential not found"));
        Admin admin = Admin.builder()
                .name(request.getName())
                .userCredential(userCredential)
                .build();
        repository.saveAndFlush(admin);
        return mapToResponse(admin);
    }

    @Transactional(readOnly = true)
    @Override
    public AdminResponse findById(String id){
        Admin admin = findByIdOrThrowException(id);

        return mapToResponse(admin);
    }
    private Admin findByIdOrThrowException(String id) {
        return repository.findById(id).orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Admin not found");});
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

