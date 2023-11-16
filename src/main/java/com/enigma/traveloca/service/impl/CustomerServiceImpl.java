package com.enigma.traveloca.service.impl;

import com.enigma.traveloca.dto.request.create.CreateCustomerRequest;
import com.enigma.traveloca.dto.request.update.UpdateCustomerRequest;
import com.enigma.traveloca.dto.response.CustomerResponse;
import com.enigma.traveloca.entity.Customer;
import com.enigma.traveloca.entity.UserCredential;
import com.enigma.traveloca.repository.CustomerRepository;
import com.enigma.traveloca.repository.UserCredentialRepository;
import com.enigma.traveloca.service.CustomerService;
import com.enigma.traveloca.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;
    private final UserCredentialRepository userCredentialRepository;
    private final ValidationUtil validationUtil;

    @Transactional(readOnly = true)
    @Override
    public List<CustomerResponse> findAll() {
        return repository.findAll().stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse save(CreateCustomerRequest request){
        validationUtil.validate(request);
        UserCredential userCredential = userCredentialRepository.findById(request.getUserCredentialId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User credential not found"));
        Customer customer = Customer.builder()
                .name(request.getName())
                .userCredential(userCredential)
                .build();
        repository.saveAndFlush(customer);
        return mapToResponse(customer);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerResponse findById(String id){
        Customer customer = findByIdOrThrowException(id);

        return mapToResponse(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Customer customer = findByIdOrThrowException(id);

        repository.delete(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public CustomerResponse update(UpdateCustomerRequest request) {
        Customer customer = findByIdOrThrowException(request.getId());

        Customer updated = Customer.builder()
                .id(customer.getId())
                .name(request.getName())
                .userCredential(customer.getUserCredential())
                .build();

        return mapToResponse(repository.saveAndFlush(updated));
    }

    private Customer findByIdOrThrowException(String id) {
        return repository.findById(id).orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");});
    }
    private CustomerResponse mapToResponse(Customer customer) {
        String userCredentialId;
        if (customer.getUserCredential() == null)  userCredentialId = null;
        else userCredentialId = customer.getUserCredential().getId();

        return CustomerResponse.builder()
                .name(customer.getName())
                .id(customer.getId())
                .userCredentialId(userCredentialId)
                .build();
    }
}
