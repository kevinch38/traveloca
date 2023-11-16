package com.enigma.traveloca.repository;

import com.enigma.traveloca.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, String> {
}
