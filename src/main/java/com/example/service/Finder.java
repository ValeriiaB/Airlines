package com.example.service;


import com.example.Authentication.SecurityService;
import com.example.DBase.Airport;
import com.example.DBase.Flight;
import com.example.DBase.Tickets;
import com.example.DBase.Users;
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
    @Autowired
    AirportRepository airportRepository;
    @Autowired
    private SecurityService securityService;

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static Calendar calendar = Calendar.getInstance();
    public List<Long> showFreePlaces(Long idFlight){
         long max=flightRepository.findByIdFlight(idFlight).getCapacity();
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

    public Airport findAirport(Long idAirport){
        return airportRepository.findOne(idAirport);
    }
    public List<Flight>  findAllFlights(){
        List<Flight> flights= new ArrayList<>();
        flightRepository.findAll()
                .forEach(flights::add);
        return flights;
    }
    public Tickets findTicket(Long idTicket){return ticketsRepository.findByIdTicket(idTicket);}
    public Flight findFlight(Long idFlight){return flightRepository.findByIdFlight(idFlight);}

    public Long findFree(Long id){
        Long tacked= ticketsRepository.countByIdFlight(id);
        Long capacity=flightRepository.findByIdFlight(id).getCapacity();
        if(capacity-tacked>0)
            return tacked+1;
        else
            return capacity-tacked;
    }

    public List<Tickets>findTickets(){
        Long idUser=securityService.getAuthenticatedUser().getIdUser();
        return ticketsRepository.findByIdUser(idUser);
    }
    public List<Flight> findByDirectionsOnDate(String from, String to,String date){
        return flightRepository.findByDirectionFromAndDirectionToAndDate(from,to,date);}
    public List<Flight> findByDate(String date){
        return flightRepository.findByDate(date);
    }
    public String isAdmin(Long id){
        return usersRepository.findOne(id).getPosition();
    }
    public List<Flight> findByAirport(Long idArport){
        return flightRepository.findByIdAirport(idArport);
    }
    public Tickets isContains(Long idFlight, Long idUser){
        return ticketsRepository.findByIdFlightAndIdUser(idFlight,idUser);
    }

    //public Float findPrice(Long idFlight){return flightRepository.findByIdFlight(idFlight).getPrice();}
    public Users findUser(Long id){return usersRepository.findOne(id);}
    public Users findUserByEmail(String email){return usersRepository.findByEmail(email);}
}
