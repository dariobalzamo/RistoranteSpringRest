package com.ristorante.spring.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ristorante.spring.model.Tavolo;

@Service // Livello di Servizio: implementa i metodi per la gestione delle operazioni CRUD o altri servizi 
public class ServiceTavolo implements IServiceTavolo{
	
	
	// Metodo di servizio per l'assegnazione automatica del tavolo 
	@Override
	public int assegnazioneTavolo(List<Tavolo> tavoli, int numero_persone) {
		int i, j, posti; 
		Tavolo tavolo = null;
		
		for(i = 0; i <= 2; i++) {
			posti = numero_persone + i;
			
			for(j = 0; j < tavoli.size(); j++) {
				tavolo = tavoli.get(j);
				if( !(tavolo.isOccupato()) && tavolo.getNumero_posti() == posti) {
					return tavolo.getId();
				}
			}
		}
		return 0;
	}
	
	
	
	// Metodo di servizio per identificare se un tavolo è occupato oppure è libero.
	@Override
	public void cambioOccupazione(Tavolo tavolo) {
		System.out.println(tavolo.isOccupato());
		if(tavolo.isOccupato())
			tavolo.setOccupato(false);
		else 
			tavolo.setOccupato(true);
	}


	
	// Metodo di servizio per il calcolo del conto totale
	@Override
	public double cassa(List<Double> listPrezzi) {
		double totale = 0;
		
		for (double prezzo_piatto: listPrezzi)
			totale+=prezzo_piatto;
		
		return totale;
	}
	
	
}