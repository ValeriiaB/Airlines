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
        airportRepository.setFixedStatus(status,id);
    }
    public void updateBonuses(Float bonus, Long id){userRepository.setFixedBonus(bonus,id);}

}
