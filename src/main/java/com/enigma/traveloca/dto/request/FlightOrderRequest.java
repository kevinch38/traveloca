package com.enigma.traveloca.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightOrderRequest {
    @NotBlank(message = "flight code is required")
    private String flightCode;
    @NotBlank(message = "airline is required")
    private String airline;
    @NotBlank(message = "from is required")
    private String from;
    @NotBlank(message = "to is required")
    private String to;
    @NotNull(message = "quantity is required")
    private Integer quantity;
}
