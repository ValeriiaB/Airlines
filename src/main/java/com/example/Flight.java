package com.example;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "flight")
public class Flight implements Serializable {
    @Id
    @GeneratedValue
    public Long idFlight;
    public Long idAirport;
    public Long idCompany;
    public String directionFrom;
    public String directionTo;
    public String date;
    public String time;
    public Long capacity;
    public Float price;
}

