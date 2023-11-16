package com.enigma.traveloca.repository.impl;

import com.enigma.traveloca.entity.Customer;
import com.enigma.traveloca.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    private final EntityManager entityManager;

    @Override
    public Customer save(Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM m_customer";

        Query query = entityManager.createNativeQuery(sql, Customer.class);

        return (List<Customer>) query.getResultList();
    }

    @Override
    public Customer findById(String id) {
        String sql = "SELECT * FROM m_customer WHERE id = ?1";

        Query query = entityManager.createNativeQuery(sql, Customer.class);
        query.setParameter(1, id);

        return (Customer) query.getSingleResult();
    }

    @Override
    public void delete(Customer customer) {
        entityManager.remove(customer);
    }

    @Override
    public Customer update(Customer customer) {

        Customer existingCustomer = findById(customer.getId());

        existingCustomer.setName(customer.getName());

        return entityManager.merge(existingCustomer);
    }
}
