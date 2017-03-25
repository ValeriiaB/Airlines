package com.example;

import com.example.service.AdminUpdates;
import com.example.service.Finder;
import com.example.service.TicketUpdates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	@Bean
	public Finder getFinder() {
		return new Finder();
	}

	@Bean
	public AdminUpdates getAdminUpdates(){return new AdminUpdates();}
	@Bean
	public TicketUpdates getTicketUpdates(){return new TicketUpdates();}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
