package com.enigma.traveloca.controller;

import com.enigma.traveloca.dto.request.update.UpdateAdminRequest;
import com.enigma.traveloca.dto.request.update.UpdateCustomerRequest;
import com.enigma.traveloca.dto.response.CommonResponse;
import com.enigma.traveloca.dto.response.AdminResponse;
import com.enigma.traveloca.dto.response.CustomerResponse;
import com.enigma.traveloca.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admins")
public class AdminController {
    private final AdminService service;
    @GetMapping
    public ResponseEntity<?> findAll() {
        List<AdminResponse> adminResponses = service.findAll();
        CommonResponse<List<AdminResponse>> response = CommonResponse.<List<AdminResponse>>builder()
                .message("Successfully get all admins")
                .statusCode(HttpStatus.OK.value())
                .data(adminResponses)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        AdminResponse adminResponse = service.findById(id);
        CommonResponse<AdminResponse> response = CommonResponse.<AdminResponse>builder()
                .message("Successfully get admin")
                .statusCode(HttpStatus.OK.value())
                .data(adminResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<?> findById(@RequestBody UpdateAdminRequest request) {
        AdminResponse adminResponse = service.update(request);
        CommonResponse<AdminResponse> response = CommonResponse.<AdminResponse>builder()
                .message("Successfully update admin")
                .statusCode(HttpStatus.OK.value())
                .data(adminResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .message("Successfully delete admin")
                .statusCode(HttpStatus.OK.value())
                .data("Ok")
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
