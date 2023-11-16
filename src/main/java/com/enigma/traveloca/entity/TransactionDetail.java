package com.enigma.traveloca.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "t_transaction_detail")
public class TransactionDetail {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @Column
    private String customerName;
    @ManyToOne
    @JoinColumn(name = "flight_code")
    private Flight flight;
    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;
    @Column
    private Long price;
}
