package com.example.service;


import com.example.Flight;
import com.example.Tickets;
import com.example.Users;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.soap.SOAPBinding;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class Finder {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    TicketsRepository ticketsRepository;
//    public void deleteTicketFromDB(Long idFlight, String name, String surname){
//        Tickets ticket=ticketsRepository.findByIdFlightAndNameAndSurname(idFlight, name, surname);
//        if(ticket!=null) {
//            ticketsRepository.delete(ticket);
//        }
//    }
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
    public Tickets findTicket(Long idUser, Long idFlight){
        return ticketsRepository.findByIdUserAndIdFlight(idUser,idFlight);
    }
    public String isAdmin(Long id){
        return usersRepository.findOne(id).position;
    }
    public List<Flight> findByAirport(Long idArport){
        return flightRepository.findByIdAirport(idArport);
    }
    public Tickets isContains(Long idFlight, Long idUser){
        return ticketsRepository.findByIdFlightAndIdUser(idFlight,idUser);
    }

    public Float findPrice(Long idFlight){return flightRepository.findByIdFlight(idFlight).price;}
    public Users findUser(Long id){return usersRepository.findOne(id);}
}
