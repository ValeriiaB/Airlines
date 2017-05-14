package com.example.Controllers;

import com.example.Authentication.SecurityService;
import com.example.DBase.Airport;
import com.example.DBase.Flight;
import com.example.DBase.Tickets;
import com.example.DBase.Users;
import com.example.service.AdminUpdates;
import com.example.service.Finder;
import com.example.service.TicketUpdates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.security.krb5.internal.Ticket;

import java.io.IOException;
import java.util.List;
@RestController
public class UserController {

    @Autowired
    private Finder finder;
    @Autowired
    private TicketUpdates ticketUpdates;
    @Autowired
    private AdminUpdates adminUpdates;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/cancel/{idTicket}",method = RequestMethod.DELETE)
    public ResponseEntity cancelBooking(@PathVariable Long idTicket){
        ticketUpdates.deleteTicketFromDB(idTicket);
        return new ResponseEntity( HttpStatus.OK);
    }

    @RequestMapping(value = "/currentUser", method = RequestMethod.GET)
    public ResponseEntity<?> getCurrentUser() {
        Users user = securityService.getAuthenticatedUser();
        return new ResponseEntity<Object>(user, HttpStatus.OK);
    }

    @RequestMapping("/mytickets")
    public List<Tickets> showUsersTickets(){
        return finder.findTickets();
    }

//    @RequestMapping(value = "/user/freePlaces/{idFlight}", method = RequestMethod.GET)
//    public String bookTicket(@PathVariable Long idFlight ) throws IOException {
//        Long idUser=securityService.getAuthenticatedUser().getIdUser();
//        if(finder.isContains(idFlight, idUser)!=null)
//            return "You already have a ticket";
//        List<Long> free=finder.showFreePlaces(idFlight);
//        if(free.size()==0)
//            return "No tickets";
//        else
//            return free.toString();
//    }

    @RequestMapping(value = "/user/bookTicket/{idFlight}",method = RequestMethod.POST)//order
    public ResponseEntity booking(@PathVariable Long idFlight){
        Long idUser=securityService.getAuthenticatedUser().getIdUser();
        Tickets ticket=new Tickets();
        Long place=finder.findFree(idFlight);
        if(place <= 0)
            return new ResponseEntity( HttpStatus.valueOf("No free places"));
        ticket=createTicket(idFlight,idUser,place);
        ticketUpdates.putTicketToDB(ticket);
        /*update user`s bonus*/
        return new ResponseEntity( HttpStatus.OK);
    }

    @RequestMapping(value = "/bookTicket",method = RequestMethod.PATCH)
    public Tickets booking(@RequestBody Tickets ticket){
        Long place=finder.findFree(ticket.getIdFlight());
//        if(place <= 0)
//            return new ResponseEntity( HttpStatus.valueOf("No free places"));
        Flight flight=finder.findFlight(ticket.getIdFlight());
        Airport airport = finder.findAirport(flight.getIdAirport());
        ticket.setDirectionFrom(flight.getDirectionFrom() + "(" + airport.getName() + "," + airport.getCity() + ")");
        ticket.setDirectionTo(flight.getDirectionTo());
        ticket.setPlace(place);
        ticket.setPaidAmount(flight.getPrice());
        ticket.setDeparture(flight.getDate()+"/"+flight.getTime());
        ticketUpdates.putTicketToDB(ticket);
        System.out.println(ticket.getIdTicket());
        return ticket;
    }

    @RequestMapping(value = "/bonuses",method = RequestMethod.GET)
    public float countBonus(){
        Long idUser=securityService.getAuthenticatedUser().getIdUser();
        return finder.findUser(idUser).getBonuses();
    }
//    @RequestMapping(value = "/show_ticket/{idTicket}",method = RequestMethod.GET)
//    public Tickets findTicket(@PathVariable Long idTicket){
//        System.out.println(idTicket);
//        System.out.println(finder.findTicket(idTicket).getDirectionFrom());
//        return finder.findTicket(idTicket);
//    }


    public Tickets createTicket(Long idFlight, Long idUser, Long place){
        Tickets ticket=new Tickets();
       Float fullPrice=finder.findFlight(idFlight).getPrice();
       Users user=finder.findUser(idUser);
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
