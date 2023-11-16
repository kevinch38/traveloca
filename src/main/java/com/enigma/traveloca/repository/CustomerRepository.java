package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
