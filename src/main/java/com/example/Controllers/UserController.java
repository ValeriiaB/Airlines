package com.example.Controllers;

import com.example.Authentication.SecurityService;
import com.example.DBase.Tickets;
import com.example.DBase.Users;
import com.example.service.AdminUpdates;
import com.example.service.Finder;
import com.example.service.TicketUpdates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "/user/freePlaces/{idFlight}", method = RequestMethod.GET)
    public String bookTicket(@PathVariable Long idFlight ) throws IOException {
        Long idUser=securityService.getAuthenticatedUser().getIdUser();
        if(finder.isContains(idFlight, idUser)!=null)
            return "You already have a ticket";
        List<Long> free=finder.showFreePlaces(idFlight);
        if(free.size()==0)
            return "No tickets";
        else
            return free.toString();
    }

    @RequestMapping(value = "/user/bookTicket/{idFlight}/{place}",method = RequestMethod.POST)//order
    public ResponseEntity booking(@PathVariable Long idFlight,  @PathVariable Long place){
        Long idUser=securityService.getAuthenticatedUser().getIdUser();
        Tickets ticket=new Tickets();
        Float fullPrice=finder.findPrice(idFlight);
        Users user=finder.findUser(idUser);
        System.out.println(user.getPassword());
        ticket.setIdFlight(idFlight);
        ticket.setIdUser(idUser);
        ticket.setName(user.getName());
        ticket.setSurname(user.getSurname());
        ticket.setPlace(place);
        if(user.getPosition()!=null)
            ticket.setPaidAmount(fullPrice*3/4);//count bonuses for admin and other;
        else{
            ticket.setPaidAmount(fullPrice);
            adminUpdates.updateBonuses(fullPrice*3/100,idUser);
        }
        ticketUpdates.putTicketToDB(ticket);
        /*update user`s bonus*/
        return new ResponseEntity( HttpStatus.OK);
    }

    @RequestMapping(value = "/bonuses",method = RequestMethod.GET)
    public float countBonus(){
        Long idUser=securityService.getAuthenticatedUser().getIdUser();
        return finder.findUser(idUser).getBonuses();
    }
}
