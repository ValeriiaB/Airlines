package com.example;

import com.example.service.AdminUpdates;
import com.example.service.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    AdminUpdates adminUpdate;
    @Autowired
    Finder finder;

    @RequestMapping(value = "/addFlight", method = RequestMethod.POST)
    public ResponseEntity addFlight(@RequestBody Flight flight) throws IOException {
        adminUpdate.putFlightToDB(flight);
        return new ResponseEntity( HttpStatus.OK);
    }
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST)
    public ResponseEntity addCompany(@RequestBody Administration admin) throws IOException {
        adminUpdate.putCAdminToDB(admin);
        return new ResponseEntity( HttpStatus.OK);
    }

    @RequestMapping(value = "/addCompany", method = RequestMethod.POST)
    public ResponseEntity addCompany(@RequestBody ShippingCompanies company) throws IOException {
        adminUpdate.putCompanyToDB(company);
        return new ResponseEntity( HttpStatus.OK);
    }
    @RequestMapping(value = "/{idAdmin}/bookTicket/{idFlight}", method = RequestMethod.GET)
    public String bookTicket(@PathVariable Long idFlight, @PathVariable Long idAdmin ) throws IOException {
        if(adminUpdate.isContains(idFlight, idAdmin)!=null)
            return "You already have a ticket";
        List<Long> free=finder.showFreePlaces(idFlight);
        if(free.size()==0)
            return "No tickets";
        else
            return free.toString();
    }

    @RequestMapping(value = "/{idAdmin}/bookTicket/{idFlight}",method = RequestMethod.POST)
    public ResponseEntity booking(@PathVariable Long idFlight, @PathVariable Long idAdmin,  @RequestBody Long place){
        Tickets ticket=new Tickets();
        Float fullPrice=adminUpdate.findPrice(idFlight);
        ticket.idFlight=idFlight;
        ticket.idAdmin=idAdmin;
        ticket.place=place;
        ticket.paidAmount=fullPrice*3/4;
        adminUpdate.putTicketToDB(ticket);
        return new ResponseEntity( HttpStatus.OK);
    }



    /* add new shipping company+
        add new flight+
         change flight
        get ticket with a discount
        cancel booking
    */


}
