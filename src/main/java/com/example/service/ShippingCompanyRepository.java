package com.example.service;


import com.example.DBase.ShippingCompanies;
import org.springframework.data.repository.CrudRepository;

public interface ShippingCompanyRepository extends CrudRepository<ShippingCompanies,Long>{
}
