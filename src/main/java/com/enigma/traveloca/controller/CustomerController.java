package com.enigma.traveloca.controller;

import com.enigma.traveloca.dto.request.update.UpdateCustomerRequest;
import com.enigma.traveloca.dto.response.CommonResponse;
import com.enigma.traveloca.dto.response.CustomerResponse;
import com.enigma.traveloca.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService service;
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<CustomerResponse> customerResponses = service.findAll();
        CommonResponse<List<CustomerResponse>> response = CommonResponse.<List<CustomerResponse>>builder()
                .message("Successfully get all customers")
                .statusCode(HttpStatus.OK.value())
                .data(customerResponses)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        CustomerResponse customerResponse = service.findById(id);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .message("Successfully get customer")
                .statusCode(HttpStatus.OK.value())
                .data(customerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<?> findById(@RequestBody UpdateCustomerRequest request) {
        CustomerResponse customerResponse = service.update(request);
        CommonResponse<CustomerResponse> response = CommonResponse.<CustomerResponse>builder()
                .message("Successfully update customer")
                .statusCode(HttpStatus.OK.value())
                .data(customerResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .message("Successfully delete customer")
                .statusCode(HttpStatus.OK.value())
                .data("Ok")
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
