package com.example.service;


import com.example.DBase.Flight;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends CrudRepository <Flight, Long>{
   // List<Flight> findByDirectionFromAndDirectionTo(String directionFrom, String directionTo);
    List<Flight> findByDate(String date);
    Flight findByIdFlight(Long idFlight);
    List<Flight> findByDirectionFromAndDirectionToAndDate(String directionFrom, String directionTo,String date);
    List<Flight> findByIdAirport(Long idAirport);
    //List<Flight> findByDateGreaterThanEqual(String currentDate, String date);
    @Query("SELECT t FROM Flight t where t.date>=(:currentDate) and t.directionFrom=(:directionFrom) and t.directionTo=(:directionTo)" )
    List<Flight> searchByDirections(@Param("currentDate")String currentDate, @Param("directionFrom")String directionFrom, @Param("directionTo")String directionTo);
    @Query("SELECT t FROM Flight t where t.date>=?1 and t.date<=?2" )
    List<Flight>  searchByPeriod(String startDate,String endDate);
    //List<Flight>  searchByPeriod(@Param("startDate") String startDate,@Param("endDate") String endDate);

}
