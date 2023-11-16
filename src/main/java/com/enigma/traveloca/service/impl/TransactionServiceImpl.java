package com.enigma.traveloca.service.impl;

import com.enigma.traveloca.dto.request.TransactionDetailRequest;
import com.enigma.traveloca.dto.request.TransactionRequest;
import com.enigma.traveloca.dto.response.TransactionDetailResponse;
import com.enigma.traveloca.dto.response.TransactionResponse;
import com.enigma.traveloca.entity.Customer;
import com.enigma.traveloca.entity.Flight;
import com.enigma.traveloca.entity.Transaction;
import com.enigma.traveloca.entity.TransactionDetail;
import com.enigma.traveloca.repository.TransactionRepository;
import com.enigma.traveloca.service.CustomerService;
import com.enigma.traveloca.service.FlightService;
import com.enigma.traveloca.service.TransactionDetailService;
import com.enigma.traveloca.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final CustomerService customerService;
    private final TransactionDetailService transactionDetailService;
    private final FlightService flightService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public TransactionResponse save(TransactionRequest request) {
        Customer customer = customerService.getById(request.getCustomerId());

        List<TransactionDetailRequest> transactionDetailRequests = new ArrayList<>();
        for (TransactionDetailRequest detailRequest : request.getRequests()) {
            Flight flight = flightService.findByFlightCode(detailRequest.getFlightCode());

            TransactionDetailRequest transactionDetail = TransactionDetailRequest.builder()
                    .customerName(detailRequest.getCustomerName())
                    .flightCode(flight.getFlightCode())
                    .build();

            transactionDetailRequests.add(transactionDetail);
        }
        List<TransactionDetail> transactionDetails = transactionDetailService.saveBulk(transactionDetailRequests);

        Transaction transaction = repository.save(Transaction.builder()
                .customer(customer)
                .transactionDetailList(transactionDetails)
                .date(LocalDateTime.now())
                .build());

        for (TransactionDetail transactionDetail : transaction.getTransactionDetailList())
            transactionDetail.setTransaction(transaction);

        return mapToTransactionResponse(transaction);
    }

    @Transactional(readOnly = true)
    @Override
    public Transaction getById(String transactionId) {
        return repository.findById(transactionId);
    }

    @Transactional(readOnly = true)
    @Override
    public TransactionResponse findById(String id) {
        return mapToTransactionResponse(repository.findById(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<TransactionResponse> findAll() {
        return repository.findAll().stream().map(this::mapToTransactionResponse)
                .collect(Collectors.toList());
    }

    private TransactionResponse mapToTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .customerName(transaction.getCustomer().getName())
                .date(transaction.getDate())
                .transactionDetails(transaction.getTransactionDetailList().stream().map(
                        transactionDetail ->
                                TransactionDetailResponse.builder()
                                        .customerName(transactionDetail.getCustomerName())
                                        .price(transactionDetail.getPrice())
                                        .flightId(transactionDetail.getFlight().getId())
                                        .build()
                ).collect(Collectors.toList()))
                .build();
    }
}
