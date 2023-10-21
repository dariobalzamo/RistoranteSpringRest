package com.ristorante.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/* 
 * @SpringBootApplication:
 * Una classe annotata con l'annotazione SpringBootApplication ha un metodo statico main
 * che, come sappiamo, consente di avviare un programma.
 * Il metodo main ha una sola istruzione: la chiamata del metodo SpringApplication.run.
 * Il metodo SpringApplication.run setta le configurazioni di default.
 * 
 * Partendo da questa classe (@SpringBootApplication) esegue lo scan di tutte le altre classi,
 * per trovare quelle importanti in modo da far partire il contesto di Spring che avrannno 
 * le annotations del tipo: 
 * - @Controller o @RestController
 * - @Service
 * - @Entity 
 * - @Repository
 * Ognuna di queste annotations ha un significato ben preciso in Spring. 
 * Questo avviene grazie a SpringBoot, un modo per evitare di riscrivere codice boilerplate;
 * evitando di scrivere le configurazioni dei componenti nei file XML.  
 */



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
