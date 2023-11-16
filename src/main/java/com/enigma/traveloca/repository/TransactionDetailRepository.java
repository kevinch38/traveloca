package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.TransactionDetail;

import java.util.List;

public interface TransactionDetailRepository {
    List<TransactionDetail> saveAll(List<TransactionDetail> transactionDetails);
}
