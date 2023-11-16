package com.enigma.traveloca.service;

import com.enigma.traveloca.dto.request.create.CreateCustomerRequest;
import com.enigma.traveloca.dto.request.update.UpdateCustomerRequest;
import com.enigma.traveloca.dto.response.CustomerResponse;
import org.hibernate.tool.hbm2ddl.SchemaUpdateCommand;

import java.util.List;

public interface CustomerService {
    CustomerResponse save(CreateCustomerRequest request);
    List<CustomerResponse> findAll();
    CustomerResponse findById(String id);
    void deleteById(String id);
    CustomerResponse update(UpdateCustomerRequest request);
}
