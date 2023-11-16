package com.enigma.traveloca.dto.request.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateFlightRequest {
    @NotBlank(message = "flight code is required")
    private String flightCode;
    @NotNull(message = "date is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
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
