package com.example.DBase;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Tickets")
public class Tickets implements Serializable {
    @Id
    @GeneratedValue
    private Long idTicket;
    private Long idFlight;
    private Long idUser;//null if table Users doesn`t contain this user
    private String name;//null if idUser or idAdmin isn`t null
    private String surname;//null if idUser or idAdmin isn`t null
    private String directionFrom;
    private String directionTo;
    private String departure;
    private Long place;
    private Float paidAmount;
}
