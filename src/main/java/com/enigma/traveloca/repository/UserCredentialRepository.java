package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.UserCredential;

public interface UserCredentialRepository {
    UserCredential findByUsername(String username);
    UserCredential findById(String id);
    UserCredential save(UserCredential userCredential);
}
