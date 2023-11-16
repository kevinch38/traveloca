package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.Admin;
import com.enigma.traveloca.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);
    List<Customer> findAll();
    Customer findById(String id);
    void delete(Customer customer);
    Customer update(Customer customer);
}
