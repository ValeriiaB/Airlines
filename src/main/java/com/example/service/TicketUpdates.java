package com.example.service;


import com.example.DBase.Tickets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TicketUpdates {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    UsersRepository userRepository;

    public void putTicketToDB(Tickets ticket){ticketsRepository.save(ticket);}
    @Transactional
    public void deleteTicketFromDB(Long idTicket){ticketsRepository.delete(idTicket);}
}
