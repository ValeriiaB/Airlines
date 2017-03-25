package com.example;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Tickets")
public class Tickets implements Serializable {
    @Id
    @GeneratedValue
    public Long idTicket;
    public Long idFlight;
    public Long idUser;//null if table Users doesn`t contain this user
    public Long idAdmin;//null if it`s not admin
    public String name;//null if idUser or idAdmin isn`t null
    public String surname;//null if idUser or idAdmin isn`t null
    public Long place;
    public Float paidAmount;
}
