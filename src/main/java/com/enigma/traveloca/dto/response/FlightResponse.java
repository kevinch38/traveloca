package com.enigma.traveloca.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightResponse {
    private String flightCode;
    private String from;
    private String to;
    private String date;
    private String time;
    private String airline;
    private Long price;
}
