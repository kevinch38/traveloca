package com.enigma.traveloca.repository.impl;

import com.enigma.traveloca.entity.UserCredential;
import com.enigma.traveloca.repository.UserCredentialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
@RequiredArgsConstructor
@Repository
public class UserCredentialRepositoryImpl implements UserCredentialRepository {
    private final EntityManager entityManager;
    @Override
    public UserCredential findByUsername(String username) {
        String sql = "SELECT * FROM m_user_credential WHERE username = ?1";

        Query query = entityManager.createNativeQuery(sql, UserCredential.class);
        query.setParameter(1, username);

        return (UserCredential) query.getSingleResult();
    }

    @Override
    public UserCredential findById(String id) {
        String sql = "SELECT * FROM m_user_credential WHERE id = ?1";

        Query query = entityManager.createNativeQuery(sql, UserCredential.class);
        query.setParameter(1, id);

        return (UserCredential) query.getSingleResult();
    }

    @Override
    public UserCredential save(UserCredential userCredential) {
        entityManager.persist(userCredential);
        return userCredential;
    }


}
