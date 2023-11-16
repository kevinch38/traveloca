package com.enigma.traveloca.repository.impl;

import com.enigma.traveloca.entity.TransactionDetail;
import com.enigma.traveloca.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
@RequiredArgsConstructor
@Repository
public class TransactionDetailRepositoryImpl implements TransactionDetailRepository {
    private final EntityManager entityManager;
    @Override
    public List<TransactionDetail> saveAll(List<TransactionDetail> transactionDetails) {
        for (TransactionDetail transactionDetail : transactionDetails) {
            entityManager.persist(transactionDetail);
        }
        return transactionDetails;
    }
}
