package com.example.service;


import com.example.Flight;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FlightRepository extends CrudRepository <Flight, Long>{
    List<Flight> findByDirectionFromAndDirectionTo(String directionFrom, String directionTo);
    List<Flight> findByDate(String date);
    Flight findByIdFlight(Long idFlight);
    List<Flight> findByDirectionFromAndDirectionToAndDate(String directionFrom, String directionTo,String date);

}
