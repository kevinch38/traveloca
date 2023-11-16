package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.Transaction;

import java.util.List;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
    List<Transaction> findAll();
    Transaction findById(String id);
}
