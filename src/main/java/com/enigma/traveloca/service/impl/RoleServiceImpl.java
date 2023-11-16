package com.enigma.traveloca.service.impl;

import com.enigma.traveloca.entity.Role;
import com.enigma.traveloca.repository.impl.RoleRepositoryImpl;
import com.enigma.traveloca.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepositoryImpl roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role getOrSave(Role role) {
        try {
            return roleRepository.findByName(role.getName());
        } catch (Exception ex) {
            System.out.println(role);
            return roleRepository.save(role);
        }
    }
}

