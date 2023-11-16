package com.enigma.traveloca.service.impl;

import com.enigma.traveloca.entity.Role;
import com.enigma.traveloca.repository.RoleRepository;
import com.enigma.traveloca.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role getOrSave(Role role) {
        Optional<Role> optionalRole = roleRepository.findByName(role.getName());
        return optionalRole.orElseGet(() -> roleRepository.save(role));
    }
}

