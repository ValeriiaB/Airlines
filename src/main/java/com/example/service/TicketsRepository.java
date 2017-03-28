package com.example.service;


import com.example.Flight;
import com.example.Tickets;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TicketsRepository extends CrudRepository<Tickets, String> {
    //Long countByIdFlightAndDate(Long idFlight, String date);
    Tickets findByIdFlightAndIdUser( Long idFlight, Long idUser);
    List <Tickets> findByIdFlight(Long idFlight);
    @Query("SELECT t.place FROM Tickets t where t.idFlight=(:idFlight)")
    List<Long> takedPlaces(@Param("idFlight") Long idFlight);
    //...
}
