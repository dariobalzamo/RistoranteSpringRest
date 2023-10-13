package com.ristorante.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RistoranteSpringRestApplication {

	// TEXT COLOR
	public static final String COLOR_PURPLE = "\u001B[35m";
	public static final String COLOR_GREEN = "\u001B[32m";
	
	public static void main(String[] args) {
		SpringApplication.run(RistoranteSpringRestApplication.class, args);
		
		System.out.println(" ");
		System.out.println(COLOR_GREEN + "INFO " + COLOR_PURPLE + "IN ASCOLTO SU [ 192.168.1.3:8082 ]");
	}

}
