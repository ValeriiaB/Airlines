package com.example.service;


import com.example.DBase.Tickets;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface TicketsRepository extends CrudRepository<Tickets, Long> {
    //Long countByIdFlightAndDate(Long idFlight, String date);
    Tickets findByIdFlightAndIdUser( Long idFlight, Long idUser);
    //List <Tickets> findByIdFlight(Long idFlight);
    List <Tickets> findByIdUser(Long idUser);
    Tickets findByIdTicket( Long id);
    @Query("SELECT t.place FROM Tickets t where t.idFlight=(:idFlight)")
    List<Long> takedPlaces(@Param("idFlight") Long idFlight);
    Long countByIdFlight(Long idFlight);
}
