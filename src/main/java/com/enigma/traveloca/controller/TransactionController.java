package com.enigma.traveloca.controller;

import com.enigma.traveloca.dto.request.TransactionRequest;
import com.enigma.traveloca.dto.response.CommonResponse;
import com.enigma.traveloca.dto.response.TransactionResponse;
import com.enigma.traveloca.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService service;
    @PostMapping
    public ResponseEntity<?> save(@RequestBody TransactionRequest request) {
        TransactionResponse transactionResponse = service.save(request);

        CommonResponse<TransactionResponse> response = CommonResponse.<TransactionResponse>builder()
                .message("Successfully save transaction")
                .statusCode(HttpStatus.OK.value())
                .data(transactionResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<TransactionResponse> transactionResponses = service.findAll();
        CommonResponse<List<TransactionResponse>> response = CommonResponse.<List<TransactionResponse>>builder()
                .message("Successfully get all transactions")
                .statusCode(HttpStatus.OK.value())
                .data(transactionResponses)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        TransactionResponse transactionResponse = service.findById(id);
        CommonResponse<TransactionResponse> response = CommonResponse.<TransactionResponse>builder()
                .message("Successfully get transaction")
                .statusCode(HttpStatus.OK.value())
                .data(transactionResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
