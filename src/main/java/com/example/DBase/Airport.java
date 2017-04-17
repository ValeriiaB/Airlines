package com.example.DBase;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Airport")
@AllArgsConstructor
@NoArgsConstructor
public class Airport  implements Serializable {
    @Id
    @GeneratedValue
    private Long idAirport;
    private String name;
    private String country;
    private String city;
    private String phone;
    private String adress;
    private Boolean isActive;
}
