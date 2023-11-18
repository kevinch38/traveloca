package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.Customer;

import java.util.List;

public interface CustomerRepository {
    Customer save(Customer customer);
    List<Customer> findAll();
    Customer findById(String id);
    void delete(Customer customer);
    Customer update(Customer customer);
}
