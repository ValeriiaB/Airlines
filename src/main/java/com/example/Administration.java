package com.example;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Administration")
public class Administration implements Serializable{
    @Id
    @GeneratedValue
    public Long idAdmin;
    public String name;
    public String surname;
    public String position;
}
