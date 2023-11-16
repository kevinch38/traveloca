package com.enigma.traveloca.repository;

import com.enigma.traveloca.constant.ERole;
import com.enigma.traveloca.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByName(ERole name);
}
