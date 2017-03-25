package com.example.service;


import com.example.Administration;
import com.example.Flight;
import com.example.ShippingCompanies;
import com.example.Tickets;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AdminUpdates {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    ShippingCompanyRepository shippingCompanyRepository;
    @Autowired
    AdminRepository adminRepository;
    public void putFlightToDB(Flight flight) {
        flightRepository.save(flight);
    }
    public void putCAdminToDB(Administration admin){adminRepository.save(admin);}
    public void putCompanyToDB(ShippingCompanies company){shippingCompanyRepository.save(company);}
    public Float findPrice(Long idFlight){return flightRepository.findByIdFlight(idFlight).price;}
    public Tickets isContains(Long idFlight, Long idAdmin){
       return ticketsRepository.findByIdFlightAndIdAdmin(idFlight,idAdmin);
    }
    public void putTicketToDB(Tickets tickets){
        ticketsRepository.save(tickets);
    }
}
