package com.enigma.traveloca.service;

import com.enigma.traveloca.dto.request.create.CreateFlightRequest;
import com.enigma.traveloca.dto.request.update.UpdateFlightRequest;
import com.enigma.traveloca.dto.response.FlightResponse;
import com.enigma.traveloca.entity.Flight;

import java.util.List;

public interface FlightService {
    FlightResponse save(CreateFlightRequest request);
    List<FlightResponse> findAll();
    FlightResponse findById(String id);
    void deleteById(String id);
    FlightResponse update(UpdateFlightRequest request);
    Flight findByFlightCode(String flightCode);
}
