package com.example;

import com.example.service.AdminUpdates;
import com.example.service.Finder;
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

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
