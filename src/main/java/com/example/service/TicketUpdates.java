package com.example.service;


import com.example.Tickets;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketUpdates {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    UsersRepository userRepository;

    public void putTicketToDB(Tickets ticket){ticketsRepository.save(ticket);}
    public void deleteTicketFromDB(Tickets ticket){ticketsRepository.delete(ticket);}
}
