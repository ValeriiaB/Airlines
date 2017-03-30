package com.example.service;


import com.example.*;
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
    @Autowired
    AirportRepository airportRepository;
    public void putFlightToDB(Flight flight) {
        flightRepository.save(flight);
    }
    public void putCAdminToDB(Users user){ userRepository.save(user);}
    public void putCompanyToDB(ShippingCompanies company){shippingCompanyRepository.save(company);}
    public void putAirportToDB(Airport airport){airportRepository.save(airport);}
    public void updateAirportStatus(boolean status,Long id){
//        Boolean flag;
//        if(status.equals("true"))
//            flag=true;
//        else
//            flag=false;
        airportRepository.setFixedStatus(status,id);
    }

}
