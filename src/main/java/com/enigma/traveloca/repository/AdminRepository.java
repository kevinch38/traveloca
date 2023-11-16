package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, String> {
}
