package com.example.service;


import com.example.Airport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AirportRepository extends CrudRepository<Airport,Long>{
    @Modifying
    @Query("update Airport u set u.isActive = ?1 where u.idAirport = ?2")
    void setFixedStatus(String isActive, Long id);
}
