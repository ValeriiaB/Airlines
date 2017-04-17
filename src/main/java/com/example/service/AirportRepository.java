package com.example.service;


import com.example.DBase.Airport;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface AirportRepository extends CrudRepository<Airport,Long>{
    @Modifying
    @Transactional
    @Query("update Airport u set u.isActive = ?1 where u.idAirport = ?2")
    void setFixedStatus(Boolean isActive, Long id);
}
