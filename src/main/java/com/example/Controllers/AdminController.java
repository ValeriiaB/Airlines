package com.example.Controllers;

import com.example.Authentication.SecurityService;
import com.example.DBase.Airport;
import com.example.DBase.Flight;
import com.example.DBase.ShippingCompanies;
import com.example.service.AdminUpdates;
import com.example.service.Finder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class AdminController {
    @Autowired
    AdminUpdates adminUpdate;
    @Autowired
    Finder finder;
    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = "/user/addFlight", method = RequestMethod.POST)
    public String addFlight(@RequestBody Flight flight) throws IOException {
        Long idAdmin=securityService.getAuthenticatedUser().getIdUser();
        if(finder.isAdmin(idAdmin)!=null) {
            adminUpdate.putFlightToDB(flight);
            return "Complited!";
        }
        else
            return "You don`t have an access";
    }
//    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
//    public ResponseEntity addUser(@RequestBody Users user) throws IOException {
//        adminUpdate.putCAdminToDB(user);
//        return new ResponseEntity( HttpStatus.OK);
//    }

    @RequestMapping(value = "/user/addCompany", method = RequestMethod.POST)
    public String addCompany(@RequestBody ShippingCompanies company) throws IOException {
        Long idAdmin=securityService.getAuthenticatedUser().getIdUser();
        if(finder.isAdmin(idAdmin)!=null) {
            adminUpdate.putCompanyToDB(company);
            return "Complited!";
        }
        else
            return "You don`t have an access";
    }

    @RequestMapping(value = "/user/addAirport", method = RequestMethod.POST)
    public String addAirport(@RequestBody Airport airport) throws IOException {
        Long idAdmin=securityService.getAuthenticatedUser().getIdUser();
        if(finder.isAdmin(idAdmin)!=null) {
            adminUpdate.putAirportToDB(airport);
            return "Complited!";
        }
        else
            return "You don`t have an access";
    }

    @RequestMapping(value = "/user/update/Airport", method = RequestMethod.PATCH)
    public String updateAirport(@RequestBody Airport airport) throws IOException {
        Long idAdmin=securityService.getAuthenticatedUser().getIdUser();
        if(finder.isAdmin(idAdmin)!=null) {
            adminUpdate. updateAirportStatus(airport.getIsActive(), airport.getIdAirport());
            return "Complited!";
        }
        else
            return "You don`t have an access";
    }


}