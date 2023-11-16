package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.Admin;

import java.util.List;

public interface AdminRepository {
    Admin save(Admin admin);
    List<Admin> findAll();
    Admin findById(String id);
    void delete(Admin admin);
    Admin update(Admin admin);
}
