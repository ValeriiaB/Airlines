package com.example;

import com.example.Authentication.SecurityService;
import com.example.service.AdminUpdates;
import com.example.service.Finder;
import com.example.service.TicketUpdates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.LockModeType;
import java.io.IOException;
import java.util.List;

@RestController

public class Controller {

    @Autowired
    private Finder finder;
    @Autowired
    private TicketUpdates ticketUpdates;
    @Autowired
    private AdminUpdates adminUpdates;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<?> addUserDB(@RequestBody Users user){
        adminUpdates.putCAdminToDB(user);
        securityService.autologin(user.email, user.password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public ResponseEntity<?> loginUser(@RequestBody Users user){
//        boolean ans = securityService.loginUser(user.email, user.password);
//        if (!ans)
//            return new ResponseEntity<>("Password incorrect or user does not exist", HttpStatus.FORBIDDEN);
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }
@GetMapping(value = "/login")
public String loginForm(Model model){
        model.addAttribute("login", new Users());
    return "login";
    }
    @PostMapping(value = "/login")
        public ResponseEntity<?>  loginSubmit(@ModelAttribute Users user) {
        boolean ans = securityService.loginUser(user.email, user.password);
        if (!ans)
            return new ResponseEntity<>("Password incorrect or user does not exist", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @RequestMapping(value = "/find/{from}/{to}", method = RequestMethod.GET)
    public List<Flight> FindByDirections( @PathVariable String from, @PathVariable String to ){
        return finder.findByDirections(from,to);
    }
    @RequestMapping(value = "/find_on_period/{startDate}/{endDate}", method = RequestMethod.GET)
    public List<Flight> FindOnPeriod( @PathVariable String startDate, @PathVariable String endDate ){
        return finder.findOnPeriod(startDate,endDate);
    }
    @RequestMapping(value ="/find_by_date/{date}",method = RequestMethod.GET)//just find doen`t work
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
    @RequestMapping("/flights")
    public List<Flight> showAllFlights(){
        return finder.findAllFlights();
    }
    @RequestMapping(value = "/user/bookTicket/{idFlight}", method = RequestMethod.GET)
    public String bookTicket(@PathVariable Long idFlight ) throws IOException {
       Long idUser=securityService.getAuthenticatedUser().idUser;
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
        Long idUser=securityService.getAuthenticatedUser().idUser;
        Tickets ticket=new Tickets();
        Float fullPrice=finder.findPrice(idFlight);
        Users user=finder.findUser(idUser);
        ticket.idFlight=idFlight;
        ticket.idUser=idUser;
        ticket.name=user.name;
        ticket.surname=user.surname;
        ticket.place=place;
        if(user.position!=null)
          ticket.paidAmount=fullPrice*3/4;//count bonuses for admin and other;
        else{
            ticket.paidAmount=fullPrice;
            adminUpdates.updateBonuses(fullPrice*3/100,idUser);
        }

        ticketUpdates.putTicketToDB(ticket);
        return new ResponseEntity( HttpStatus.OK);
    }

    @RequestMapping(value = "/bookTicket",method = RequestMethod.POST)
    public ResponseEntity booking(@RequestBody Tickets ticket){
        /*registration?*/
        ticketUpdates.putTicketToDB(ticket);
        return new ResponseEntity( HttpStatus.OK);
    }
    /*add cancel without id*/
    @RequestMapping(value = "/user/cancel/{idFlight}",method = RequestMethod.DELETE)
    public String cancelBooking(@PathVariable Long idFlight){
        Long idUser=securityService.getAuthenticatedUser().idUser;
        Tickets ticket=finder.isContains(idFlight,idUser);
        if(ticket!=null){
            ticketUpdates.deleteTicketFromDB(ticket);
            return "Canceled";
        }
        else
            return "You don`t have a ticket";
    }
}