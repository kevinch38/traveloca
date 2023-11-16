package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, String> {
}
