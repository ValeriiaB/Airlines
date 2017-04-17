package com.example.DBase;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "flight")
@AllArgsConstructor
@NoArgsConstructor
public class Flight implements Serializable {
    @Id
    @GeneratedValue
    private Long idFlight;
    private Long idAirport;
    private Long idCompany;
    private String directionFrom;
    private String directionTo;
    private String date;
    private String time;
    private Long capacity;
    private Float price;

}

