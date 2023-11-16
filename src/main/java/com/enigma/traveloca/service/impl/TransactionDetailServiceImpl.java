package com.enigma.traveloca.service.impl;

import com.enigma.traveloca.dto.request.TransactionDetailRequest;
import com.enigma.traveloca.entity.TransactionDetail;
import com.enigma.traveloca.repository.TransactionDetailRepository;
import com.enigma.traveloca.service.FlightService;
import com.enigma.traveloca.service.TransactionDetailService;
import com.enigma.traveloca.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class TransactionDetailServiceImpl implements TransactionDetailService {
    private final FlightService flightService;
    private final TransactionDetailRepository repository;
    private final ValidationUtil validationUtil;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<TransactionDetail> saveBulk(List<TransactionDetailRequest> requests) {
        validationUtil.validate(requests);

        List<TransactionDetail> transactionDetails = new ArrayList<>();
        for (TransactionDetailRequest request : requests) {
            TransactionDetail transactionDetail = TransactionDetail.builder()
                    .flight(flightService.findByFlightCode(request.getFlightCode()))
                    .price(flightService.findByFlightCode(request.getFlightCode()).getPrice())
                    .customerName(request.getCustomerName())
                    .build();
            transactionDetails.add(transactionDetail);
        }

        return repository.saveAll(transactionDetails);
    }
}
