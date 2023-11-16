package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String> {
    Optional<Flight> findByFlightCode(String flightCode);
}
