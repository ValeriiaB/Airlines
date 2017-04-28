package com.example.Controllers;

import com.example.Authentication.SecurityService;
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
        securityService.autologin(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> loginUser(@RequestBody Users user){
        boolean ans = securityService.loginUser(user.getEmail(), user.getPassword());
        if (!ans)
            return new ResponseEntity<>("Password incorrect or user does not exist", HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/find/{from}/{to}", method = RequestMethod.GET)
    public List<Flight> FindByDirections(@PathVariable String from, @PathVariable String to ){
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


}