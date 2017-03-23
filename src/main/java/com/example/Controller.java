package com.example;

import com.example.service.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController

public class Controller {

    @Autowired
    private Finder finder;

    @RequestMapping(value = "/addFlight", method = RequestMethod.POST)
    public ResponseEntity addFlight(@RequestBody Flight flight) throws IOException {
        finder.putFlightToDB(flight);
       return new ResponseEntity( HttpStatus.OK);
    }
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
    @RequestMapping(value ="/cancel/{flight}",method = RequestMethod.DELETE)
    public ResponseEntity CancelBook(@PathVariable String flight, @RequestBody String person ){
        String[] s=person.split(" ");
        finder.deleteTicketFromDB(flight,  s[0], s[1]);
        return new ResponseEntity( HttpStatus.OK);
    }
    @RequestMapping(value = "/book/{flight}/{date}", method = RequestMethod.POST)
    public String Book( @PathVariable String flight,@PathVariable String date, @RequestBody Tickets ticket ){
       if(finder.contains(ticket.idFlight, ticket.name, ticket.surname)!=null)
           return "You already have a ticket";
        if(finder.isFree(flight,date)){
            finder.putPassengerToDB(ticket);
            return "Copmlited successfully";
        }
        else
            return "No tickets";
    }
}
