package com.example.service;


import com.example.Flight;
import com.example.Tickets;
import org.springframework.beans.factory.annotation.Autowired;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Finder {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketsRepository ticketsRepository;
    public void putFlightToDB(Flight flight) {
        flightRepository.save(flight);
    }
    public void deleteTicketFromDB(Long idFlight, String name, String surname){
        Tickets ticket=ticketsRepository.findByIdFlightAndNameAndSurname(idFlight, name, surname);
        if(ticket!=null) {
            ticketsRepository.delete(ticket);
        }
       // ticketsRepository.deleteByIdFlightAndNameAndSurnameAndDate(idFlight, name, surname, date);
    }
    public List<Long> showFreePlaces(Long idFlight){
         long max=flightRepository.findByIdFlight(idFlight).capacity;
            List<Long> taked=ticketsRepository.takedPlaces(idFlight);
            List<Long> free=new ArrayList<>();
            for(int i=1;i<=max;i++)
                if(!taked.contains(i))
                    free.add(Long.valueOf(i));
        return free;
    }
    public List<Flight> findByDirections(String from, String to){
        return flightRepository.findByDirectionFromAndDirectionTo(from,to);}
    public List<Flight> findByDirectionsOnDate(String from, String to,String date){
        return flightRepository.findByDirectionFromAndDirectionToAndDate(from,to,date);}
    public List<Flight> findByDate(String date){
        return flightRepository.findByDate(date);
    }
    public Tickets contains(Long idFlight, String name, String surname){
        return ticketsRepository.findByIdFlightAndNameAndSurname(idFlight, name, surname);
    }

//    public boolean isFree(Long flight, String date){
//        Flight f=flightRepository.findByIdFlight(flight);
//        return  f.capacity-ticketsRepository.countByIdFlightAndDate(flight,date)>0;
//    }
    public void putPassengerToDB(Tickets tickets){
        ticketsRepository.save(tickets);
    }
}
