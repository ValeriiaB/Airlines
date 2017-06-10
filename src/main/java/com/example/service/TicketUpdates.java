package com.example.service;


import com.example.DBase.Airport;
import com.example.DBase.Flight;
import com.example.DBase.Tickets;
import com.example.DBase.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class TicketUpdates {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    UsersRepository userRepository;
    @Autowired
    Finder finder;
    @Autowired
    AdminUpdates adminUpdates;

    public void putTicketToDB(Tickets ticket){ticketsRepository.save(ticket);}
    @Transactional
    public void deleteTicketFromDB(Long idTicket){ticketsRepository.delete(idTicket);}
    public Tickets createTicket(Long idFlight, Long idUser, Long place){
        Tickets ticket=new Tickets();
        Users user=finder.findUser(idUser);
        Float fullPrice=finder.findFlight(idFlight).getPrice();
        Flight flight=finder.findFlight(idFlight);
        Airport airport = finder.findAirport(flight.getIdAirport());
        ticket.setIdFlight(idFlight);
        ticket.setIdUser(idUser);
        ticket.setName(user.getName());
        ticket.setSurname(user.getSurname());
        ticket.setPlace(place);
        ticket.setDirectionFrom(flight.getDirectionFrom() + "(" + airport.getName() + "," + airport.getCity() + ")");
        ticket.setDirectionTo(flight.getDirectionTo());
        ticket.setDeparture(flight.getDate()+"/"+flight.getTime());
        if(!user.getPosition().equals("guest"))
            ticket.setPaidAmount(fullPrice * 3 / 4);// if admin
        else{
            ticket.setPaidAmount(fullPrice);
            adminUpdates.updateBonuses((user.getBonuses() + fullPrice * 5 / 100),idUser);
        }
        return ticket;
    }
}
