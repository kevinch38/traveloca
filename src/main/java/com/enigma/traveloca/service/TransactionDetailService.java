package com.enigma.traveloca.service;

import com.enigma.traveloca.dto.request.TransactionDetailRequest;
import com.enigma.traveloca.entity.TransactionDetail;

import java.util.List;

public interface TransactionDetailService {
    List<TransactionDetail> saveBulk(List<TransactionDetailRequest> requests);
}
