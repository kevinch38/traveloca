package com.enigma.traveloca.repository.impl;

import com.enigma.traveloca.entity.Flight;
import com.enigma.traveloca.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class FlightRepositoryImpl implements FlightRepository {

    private final EntityManager entityManager;
    @Override
    public Flight save(Flight flight) {
        entityManager.persist(flight);
        return flight;
    }

    @Override
    public List<Flight> findAll() {
        String sql = "SELECT * FROM m_flight";

        Query query = entityManager.createNativeQuery(sql, Flight.class);

        return (List<Flight>) query.getResultList();
    }

    @Override
    public void delete(Flight flight) {
        entityManager.remove(flight);
    }

    @Override
    public Flight update(Flight flight) {
        Flight existingFlight = findById(flight.getId());

        existingFlight.setFlightCode(flight.getFlightCode());
        existingFlight.setId(flight.getId());
        existingFlight.setDate(flight.getDate());
        existingFlight.setAirline(flight.getAirline());
        existingFlight.setTime(flight.getTime());
        existingFlight.setPrice(flight.getPrice());
        existingFlight.setDepartFrom(flight.getDepartFrom());
        existingFlight.setDestinationTo(flight.getDestinationTo());

        return entityManager.merge(existingFlight);
    }

    @Override
    public Flight findByFlightCode(String flightCode) {
        String sql = "SELECT * FROM m_flight WHERE flight_code = ?1";

        Query query = entityManager.createNativeQuery(sql, Flight.class);
        query.setParameter(1, flightCode);

        return (Flight) query.getSingleResult();
    }

    @Override
    public Flight findById(String id) {
        String sql = "SELECT * FROM m_flight WHERE id = ?1";

        Query query = entityManager.createNativeQuery(sql, Flight.class);
        query.setParameter(1, id);

        return (Flight) query.getSingleResult();
    }
}
