package com.enigma.traveloca.service;

import com.enigma.traveloca.dto.request.create.CreateCustomerRequest;
import com.enigma.traveloca.dto.request.update.UpdateCustomerRequest;
import com.enigma.traveloca.dto.response.CustomerResponse;
import com.enigma.traveloca.entity.Customer;

import java.util.List;

public interface CustomerService {
    CustomerResponse save(CreateCustomerRequest request);
    List<CustomerResponse> findAll();
    CustomerResponse findById(String id);
    Customer getById(String id);
    void deleteById(String id);
    CustomerResponse update(UpdateCustomerRequest request);
}
