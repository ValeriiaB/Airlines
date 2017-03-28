package com.example.service;


import com.example.Flight;
import com.example.Tickets;
import com.example.Users;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Finder {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    TicketsRepository ticketsRepository;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static Calendar calendar = Calendar.getInstance();
    public List<Long> showFreePlaces(Long idFlight){
         long max=flightRepository.findByIdFlight(idFlight).capacity;
            List<Long> taked=ticketsRepository.takedPlaces(idFlight);
            List<Long> free=new ArrayList<>();
            for(int i=1;i<=max;i++)
                if(!taked.contains(i))
                    free.add(Long.valueOf(i));
        return free;
    }
    public List<Flight> findByDirections(String from, String to) {
        String currentDate = dateFormat.format(calendar.getTime());
        return flightRepository.searchByDirections(currentDate,from,to);
    }
    public List<Flight>  findOnPeriod(String startDate, String endDate){
        return flightRepository.searchByPeriod(startDate,endDate);
    }
    public List<Flight> findByDirectionsOnDate(String from, String to,String date){
        return flightRepository.findByDirectionFromAndDirectionToAndDate(from,to,date);}
    public List<Flight> findByDate(String date){
        return flightRepository.findByDate(date);
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
