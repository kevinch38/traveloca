package com.enigma.traveloca.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "m_flight")
public class Flight {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @Column(unique = true)
    private String flightCode;
    @Column
    private LocalDate date;
    @Column
    private LocalTime time;
    @Column
    private String airline;
    @Column(name = "depart_from")
    private String departFrom;
    @Column(name = "destination_to")
    private String destinationTo;
    @Column
    private Long price;
}
