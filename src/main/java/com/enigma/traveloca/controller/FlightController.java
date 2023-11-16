package com.enigma.traveloca.controller;

import com.enigma.traveloca.dto.request.create.CreateFlightRequest;
import com.enigma.traveloca.dto.request.update.UpdateFlightRequest;
import com.enigma.traveloca.dto.response.CommonResponse;
import com.enigma.traveloca.dto.response.FlightResponse;
import com.enigma.traveloca.service.FlightService;
import com.enigma.traveloca.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService service;
    @PostMapping
    public ResponseEntity<?> save(CreateFlightRequest request) {
        FlightResponse flightResponse = service.save(request);

        CommonResponse<FlightResponse> response = CommonResponse.<FlightResponse>builder()
                .message("Successfully get all flights")
                .statusCode(HttpStatus.OK.value())
                .data(flightResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<FlightResponse> flightResponses = service.findAll();
        CommonResponse<List<FlightResponse>> response = CommonResponse.<List<FlightResponse>>builder()
                .message("Successfully get all flights")
                .statusCode(HttpStatus.OK.value())
                .data(flightResponses)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        FlightResponse flightResponse = service.findById(id);
        CommonResponse<FlightResponse> response = CommonResponse.<FlightResponse>builder()
                .message("Successfully get flight")
                .statusCode(HttpStatus.OK.value())
                .data(flightResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @PutMapping
    public ResponseEntity<?> findById(@RequestBody UpdateFlightRequest request) {
        FlightResponse flightResponse = service.update(request);
        CommonResponse<FlightResponse> response = CommonResponse.<FlightResponse>builder()
                .message("Successfully update flight")
                .statusCode(HttpStatus.OK.value())
                .data(flightResponse)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        service.deleteById(id);

        CommonResponse<String> response = CommonResponse.<String>builder()
                .message("Successfully delete flight")
                .statusCode(HttpStatus.OK.value())
                .data("Ok")
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
