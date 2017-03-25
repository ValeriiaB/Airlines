package com.example;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Companies")
public class ShippingCompanies implements Serializable{
    @Id
    @GeneratedValue
    public Long idCompany;
    public String companyName;
    public String country;
    public String site;
}
