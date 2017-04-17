package com.example.DBase;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable{
    @Id
    @GeneratedValue
    private Long idUser;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String DOB;//date of birth
    private Float bonuses;//0 for registration;
    private String position;
}
