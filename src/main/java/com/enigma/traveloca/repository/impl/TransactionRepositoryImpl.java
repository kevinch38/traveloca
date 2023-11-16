package com.enigma.traveloca.repository.impl;

import com.enigma.traveloca.entity.Transaction;
import com.enigma.traveloca.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
@RequiredArgsConstructor
@Repository
public class TransactionRepositoryImpl implements TransactionRepository {
    private final EntityManager entityManager;
    @Override
    public Transaction save(Transaction transaction) {
        entityManager.persist(transaction);

        return transaction;
    }

    @Override
    public List<Transaction> findAll() {
        String sql = "SELECT * FROM t_transaction";

        Query query = entityManager.createNativeQuery(sql, Transaction.class);

        return (List<Transaction>) query.getResultList();
    }

    @Override
    public Transaction findById(String id) {
        String sql = "SELECT * FROM t_transaction WHERE id = ?1";

        Query query = entityManager.createNativeQuery(sql, Transaction.class);
        query.setParameter(1, id);


        return (Transaction) query.getSingleResult();
    }
}
