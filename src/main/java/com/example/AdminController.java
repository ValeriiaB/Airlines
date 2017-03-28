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
public class AdminController {
    @Autowired
    AdminUpdates adminUpdate;
    @Autowired
    Finder finder;

    @RequestMapping(value = "/{idAdmin}/addFlight", method = RequestMethod.POST)
    public String addFlight(@RequestBody Flight flight, @PathVariable Long idAdmin) throws IOException {
        if(finder.isAdmin(idAdmin)!=null) {
            adminUpdate.putFlightToDB(flight);
            return "Complited!";
        }
        else
            return "You don`t have an access";
    }
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ResponseEntity addCompany(@RequestBody Users user) throws IOException {
        adminUpdate.putCAdminToDB(user);
        return new ResponseEntity( HttpStatus.OK);
    }

    @RequestMapping(value = "/{idAdmin}/addCompany", method = RequestMethod.POST)
    public String addCompany(@RequestBody ShippingCompanies company,@PathVariable Long idAdmin) throws IOException {
        if(finder.isAdmin(idAdmin)!=null) {
            adminUpdate.putCompanyToDB(company);
            return "Complited!";
        }
        else
            return "You don`t have an access";
    }

    @RequestMapping(value = "/{idAdmin}/addAirport", method = RequestMethod.POST)
    public String addAirport(@RequestBody Airport airport,@PathVariable Long idAdmin) throws IOException {
        if(finder.isAdmin(idAdmin)!=null) {
            adminUpdate.putAirportToDB(airport);
            return "Complited!";
        }
        else
            return "You don`t have an access";
    }

    @RequestMapping(value = "/{idAdmin}/update/Airport", method = RequestMethod.POST)
    public String updateAirport(@RequestBody String status,@RequestBody Long id,@PathVariable Long idAdmin) throws IOException {
        if(finder.isAdmin(idAdmin)!=null) {
            adminUpdate. updateAirportStatus(status, id);
            return "Complited!";
        }
        else
            return "You don`t have an access";
    }


    /*change flight
    change airport information i.e. set isActive to false
    */


}
