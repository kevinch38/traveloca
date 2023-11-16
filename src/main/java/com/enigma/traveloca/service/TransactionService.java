package com.enigma.traveloca.service;

import com.enigma.traveloca.dto.request.TransactionRequest;
import com.enigma.traveloca.dto.response.TransactionResponse;
import com.enigma.traveloca.entity.Transaction;

import java.util.List;

public interface TransactionService {
    TransactionResponse save(TransactionRequest request);
    TransactionResponse findById(String id);
    List<TransactionResponse> findAll();

    Transaction getById(String transactionId);
}
