package com.example;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Airport")
public class Airport  implements Serializable {
    @Id
    @GeneratedValue
    public Long idAirport;
    public String name;
    public String country;
    public String city;
    public String phone;
    public String adress;
    public Boolean isActive;
}
