package com.enigma.traveloca.service;

import com.enigma.traveloca.dto.request.CreateCustomerRequest;
import com.enigma.traveloca.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {
    CustomerResponse save(CreateCustomerRequest request);
    List<CustomerResponse> findAll();
    CustomerResponse findById(String id);
}
