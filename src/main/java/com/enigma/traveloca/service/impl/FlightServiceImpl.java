package com.enigma.traveloca.service.impl;

import com.enigma.traveloca.dto.request.create.CreateFlightRequest;
import com.enigma.traveloca.dto.request.update.UpdateFlightRequest;
import com.enigma.traveloca.dto.response.FlightResponse;
import com.enigma.traveloca.entity.Flight;
import com.enigma.traveloca.repository.FlightRepository;
import com.enigma.traveloca.service.FlightService;
import com.enigma.traveloca.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository repository;
    private final ValidationUtil validationUtil;
    @Transactional(rollbackFor = Exception.class)
    @Override
    public FlightResponse save(CreateFlightRequest request) {
        validationUtil.validate(request);

        LocalTime localTime = LocalTime.parse(request.getTime());

        Flight flight = Flight.builder()
                .departFrom(request.getFrom())
                .destinationTo(request.getTo())
                .date(request.getDate())
                .time(localTime)
                .airline(request.getAirline())
                .price(request.getPrice())
                .flightCode(request.getFlightCode())
                .build();

        return mapToResponse(repository.saveAndFlush(flight));
    }

    @Transactional(readOnly = true)
    @Override
    public List<FlightResponse> findAll() {
        return repository.findAll().stream().map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public FlightResponse findById(String id) {
        return mapToResponse(findByIdOrThrowException(id));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        Flight flight = findByIdOrThrowException(id);

        repository.delete(flight);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public FlightResponse update(UpdateFlightRequest request) {
        validationUtil.validate(request);

        LocalTime localTime = LocalTime.parse(request.getTime());
        Flight flight = findByIdOrThrowException(request.getId());

        Flight updated = Flight.builder()
                .id(flight.getId())
                .departFrom(request.getFrom())
                .destinationTo(request.getTo())
                .date(request.getDate())
                .time(localTime)
                .airline(request.getAirline())
                .price(request.getPrice())
                .flightCode(flight.getFlightCode())
                .build();

        return mapToResponse(repository.saveAndFlush(updated));
    }

    @Override
    public Flight findByFlightCode(String flightCode) {
       return repository.findByFlightCode(flightCode)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));
    }

    private Flight findByIdOrThrowException(String id) {
        return repository.findById(id).orElseThrow(() -> { throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found");});
    }
    private FlightResponse mapToResponse(Flight flight) {
        return FlightResponse.builder()
                .from(flight.getDepartFrom())
                .to(flight.getDestinationTo())
                .date(flight.getDate().toString())
                .time(flight.getTime().toString())
                .airline(flight.getAirline())
                .price(flight.getPrice())
                .flightCode(flight.getFlightCode())
                .build();
    }
}
