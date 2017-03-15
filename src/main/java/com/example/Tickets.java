package com.example;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Tickets")
@IdClass(TicketsKey.class)
public class Tickets implements Serializable {
    @Id
    public String idFlight;
    public String name;
    public String surname;
    public String date;
    @Id public String place;
}
    class TicketsKey implements Serializable{
        String idFlight;
        String place;
        }