package com.example.service;


import com.example.Flight;
import com.example.Tickets;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.util.List;

public class Finder {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketsRepository ticketsRepository;
    public void putFlightToDB(Flight flight) {
        flightRepository.save(flight);
    }
    public void deleteTicketFromDB(String idFlight, String name, String surname, String date){
        ticketsRepository.deleteByIdFlightAndNameAndSurnameAndDate(idFlight, name, surname, date);
    }
    public List<Flight> findByDirections(String from, String to){
        return flightRepository.findByDirectionFromAndDirectionTo(from,to);}
    public List<Flight> findByDirectionsOnDate(String from, String to,String date){
        return flightRepository.findByDirectionFromAndDirectionToAndDate(from,to,date);}
    public List<Flight> findByDate(String date){
        return flightRepository.findByDate(date);
    }
    public Tickets contains(String idFlight, String name, String surname){
        return ticketsRepository.findByIdFlightAndNameAndSurname(idFlight, name, surname);
    }

    public boolean isFree(String flight, String date){
        Flight f=flightRepository.findByDateAndId(date,flight);
        return  f.capacity-ticketsRepository.countByIdFlightAndDate(flight,date)>0;
    }
    public void putPassengerToDB(Tickets tickets){
        ticketsRepository.save(tickets);
    }
}
