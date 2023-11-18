package com.enigma.traveloca.repository.impl;

import com.enigma.traveloca.constant.ERole;
import com.enigma.traveloca.entity.Role;
import com.enigma.traveloca.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@RequiredArgsConstructor
@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final EntityManager entityManager;
    public Role findByName(ERole name) {
        String sql = "SELECT * FROM m_role WHERE name = ?1";

        Query query = entityManager.createNativeQuery(sql, Role.class);
        query.setParameter(1, name.name());

        return (Role) query.getSingleResult();
    }

    public Role save(Role role) {
        entityManager.persist(role);
        return role;
    }
}
