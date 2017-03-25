package com.example;

import com.example.service.Finder;
import com.example.service.TicketUpdates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.LockModeType;
import java.io.IOException;
import java.util.List;

@RestController

public class Controller {

    @Autowired
    private Finder finder;
    @Autowired
    private TicketUpdates ticketUpdates;


    @RequestMapping(value = "/find/{from}/{to}", method = RequestMethod.GET)
    public List<Flight> FindByDirections( @PathVariable String from, @PathVariable String to ){
        return finder.findByDirections(from,to);
    }
    @RequestMapping(value ="/find/{date}",method = RequestMethod.GET)
    public List<Flight> FindOnDate(@PathVariable String date){
        return finder.findByDate(date);
    }
    @RequestMapping(value = "/find/{from}/{to}/{date}", method = RequestMethod.GET)
    public List<Flight> FindByDirectionsOnDate( @PathVariable String from, @PathVariable String to,@PathVariable String date ){
        return finder.findByDirectionsOnDate(from,to,date);
    }
    @RequestMapping(value = "/find/{idAirport}", method = RequestMethod.GET)
    public List<Flight> FindByDirectionsOnDate( @PathVariable Long idAirport ){
        return finder.findByAirport(idAirport);
    }

    @RequestMapping(value = "/{idUser}/bookTicket/{idFlight}", method = RequestMethod.GET)
    public String bookTicket(@PathVariable Long idFlight, @PathVariable Long idUser ) throws IOException {
        if(finder.isContains(idFlight, idUser)!=null)
            return "You already have a ticket";
        List<Long> free=finder.showFreePlaces(idFlight);
        if(free.size()==0)
            return "No tickets";
        else
            return free.toString();
    }

    @RequestMapping(value = "/{idUser}/bookTicket/{idFlight}/{place}",method = RequestMethod.POST)//order
    public ResponseEntity booking(@PathVariable Long idFlight, @PathVariable Long idUser,  @PathVariable Long place){
        Tickets ticket=new Tickets();
        Float fullPrice=finder.findPrice(idFlight);
        Users user=finder.findUser(idUser);
        ticket.idFlight=idFlight;
        ticket.idUser=idUser;
        ticket.place=place;
       // ticket.paidAmount=fullPrice*3/4;//count bonuses for admin and other; log in and redirect
        ticketUpdates.putTicketToDB(ticket);
        return new ResponseEntity( HttpStatus.OK);
    }
    @RequestMapping(value = "/{idUser}/cancel/{idFlight}",method = RequestMethod.DELETE)
    public String cancelBooking( @PathVariable Long idUser, @PathVariable Long idFlight){
        Tickets ticket=finder.findTicket(idUser,idFlight);
        if(ticket!=null){
            ticketUpdates.deleteTicketFromDB(ticket);
            return "Canceled";
        }
        else
            return "You don`t have a ticket";
    }
}
