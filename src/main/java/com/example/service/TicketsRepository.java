package com.example.service;


import com.example.Flight;
import com.example.Tickets;
import org.springframework.data.repository.CrudRepository;

public interface TicketsRepository extends CrudRepository<Tickets, String> {
    Long countByIdFlightAndDate(String idFlight, String date);
    Tickets findByIdFlightAndNameAndSurname(String idFlight, String name, String surname);
    void deleteByIdFlightAndNameAndSurnameAndDate(String idFlight, String name, String surname, String date);
    //...
}
