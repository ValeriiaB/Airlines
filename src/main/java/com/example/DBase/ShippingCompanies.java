package com.example.DBase;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "Companies")
@AllArgsConstructor
@NoArgsConstructor
public class ShippingCompanies implements Serializable{
    @Id
    @GeneratedValue
    private Long idCompany;
    private String companyName;
    private String country;
    private String site;
}
