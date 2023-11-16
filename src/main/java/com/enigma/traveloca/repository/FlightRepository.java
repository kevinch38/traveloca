package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.Flight;

import java.util.List;

public interface FlightRepository {
    Flight save(Flight flight);
    List<Flight> findAll();
    void delete(Flight flight);
    Flight update(Flight flight);
    Flight findByFlightCode(String flightCode);
    Flight findById(String id);
}
