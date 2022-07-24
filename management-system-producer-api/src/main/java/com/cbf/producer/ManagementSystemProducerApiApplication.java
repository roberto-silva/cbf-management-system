package com.cbf.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class ManagementSystemProducerApiApplication {

	public static void main(String[] args) {
		System.out.println(LocalDate.now());
		SpringApplication.run(ManagementSystemProducerApiApplication.class, args);
	}

}
