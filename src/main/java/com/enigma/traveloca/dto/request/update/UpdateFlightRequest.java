package com.enigma.traveloca.dto.request.update;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateFlightRequest {
    @NotBlank(message = "flight code is required")
    private String flightCode;
    @NotBlank(message = "username is required")
    private String id;
    @NotNull(message = "date is required")
    private LocalDate date;
    @NotBlank(message = "time is required")
    private String time;
    @NotBlank(message = "airline is required")
    private String airline;
    @NotBlank(message = "from is required")
    private String from;
    @NotBlank(message = "to is required")
    private String to;
    @NotNull(message = "price is required")
    private Long price;
}
