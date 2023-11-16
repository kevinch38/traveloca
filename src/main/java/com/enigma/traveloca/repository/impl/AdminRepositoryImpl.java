package com.enigma.traveloca.repository.impl;

import com.enigma.traveloca.entity.Admin;
import com.enigma.traveloca.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class AdminRepositoryImpl implements AdminRepository {
    private final EntityManager entityManager;

    @Override
    public Admin save(Admin admin) {
        entityManager.persist(admin);
        return admin;
    }

    @Override
    public List<Admin> findAll() {
        String sql = "SELECT * FROM m_admin";

        Query query = entityManager.createNativeQuery(sql, Admin.class);

        return (List<Admin>) query.getResultList();
    }

    @Override
    public Admin findById(String id) {
        String sql = "SELECT * FROM m_admin WHERE id = ?1";

        Query query = entityManager.createNativeQuery(sql, Admin.class);
        query.setParameter(1, id);

        return (Admin) query.getSingleResult();
    }

    @Override
    public void delete(Admin admin) {
        entityManager.remove(admin);
    }

    @Override
    public Admin update(Admin admin) {
        Admin existingAdmin = findById(admin.getId());

        existingAdmin.setName(admin.getName());

        return entityManager.merge(existingAdmin);
    }
}
