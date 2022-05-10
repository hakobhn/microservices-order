package com.example.shipping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ShippingTestApp {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ShippingTestApp.class);
		app.setAdditionalProfiles("test");
		app.run(args);
	}

}
