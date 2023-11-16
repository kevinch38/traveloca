package com.enigma.traveloca.repository;

import com.enigma.traveloca.constant.ERole;
import com.enigma.traveloca.entity.Role;

public interface RoleRepository {
    Role findByName(ERole name);
    Role save(Role name);
}
