package com.example.service;


import com.example.Flight;
import com.example.ShippingCompanies;
import com.example.Tickets;
import com.example.Users;
import org.springframework.beans.factory.annotation.Autowired;

public class AdminUpdates {
    @Autowired
    FlightRepository flightRepository;
    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    ShippingCompanyRepository shippingCompanyRepository;
    @Autowired
    UsersRepository userRepository;
    public void putFlightToDB(Flight flight) {
        flightRepository.save(flight);
    }
    public void putCAdminToDB(Users user){ userRepository.save(user);}
    public void putCompanyToDB(ShippingCompanies company){shippingCompanyRepository.save(company);}
}
