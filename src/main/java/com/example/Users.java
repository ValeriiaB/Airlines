package com.example;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Users")
public class Users implements Serializable{
    @Id
    @GeneratedValue
    public Long idUser;
    public String name;
    public String surname;
    public String DOB;//date of birth
    public Float bonuses;//0 for registration;
    public String position;
}
