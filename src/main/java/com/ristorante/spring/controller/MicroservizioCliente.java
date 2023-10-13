package com.ristorante.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ristorante.spring.dto.DtoConto;
import com.ristorante.spring.model.*;
import com.ristorante.spring.repository.*;
import com.ristorante.spring.service.ServiceTavolo;


@RestController
@RequestMapping("/cliente")   // Controller per il microservizio ristoratore
public class MicroservizioCliente {
	
	@Autowired
	IDaoPrenotazione dao_prenotazione;
	@Autowired
	ServiceTavolo serviceTavolo;
	@Autowired
	IDaoTavolo dao_tavolo;
	@Autowired
	IDaoPiatto dao_piatto;
	@Autowired
	IDaoConto dao_conto;
	
	
	
	// ############################### OPERAZIONI CLIENTE ###############################
	
	// 1) INSERIMENTO PRENOTAZIONE: PRENOTAZIONE TAVOLO
	@PostMapping("/upsertPrenotazione")
	public String upsertPrenotazione(@RequestParam int numero_persone) {
		
		// VARIABILI LOCALI 
		Prenotazione prenotazione = null;
		List<Tavolo> tavoli = null;
		int numero_tavolo = 0;
		
		// BUSINESS LOGIC
		tavoli = dao_tavolo.findAll();
		numero_tavolo = serviceTavolo.assegnazioneTavolo(tavoli, numero_persone);
		if(numero_tavolo != 0) { // SE E' DISPONIBILE UN TAVOLO, VIENE CREATA UNA PRENOTAZIONE
			prenotazione = new Prenotazione();
			prenotazione.setPagato(false);
			prenotazione.setTavolo(dao_tavolo.findById(numero_tavolo));
			serviceTavolo.cambioOccupazione(prenotazione.getTavolo());
			dao_prenotazione.save(prenotazione);
			
			return "Il tuo tavolo [ TAVOLO n." + numero_tavolo + "] è stato prenotato con successo !";
		}
		
		// RESPONSE SE NON E' DIAPONIBILE UN TAVOLO.
		return "Mi dispiace, attualmente non sono disponibili tavoli nel locale.";
	}
	
	
	
	// 2) INSERIMENTO ORDINAZIONE: ORDINAZIONE DI UN PIATTO
	@PostMapping("/upsertOrdinazione")
	public String upsertOrdinazione(@RequestParam int numero_tavolo, int id_piatto) {
		
		// VARIABILI GLOBALI
		Prenotazione prenotazione = null;
		Piatto piatto = null;
		
		// BUSINESS LOGIC
		prenotazione = dao_prenotazione.findByIdTavoloNative(numero_tavolo);
		if(prenotazione != null) {
			piatto = dao_piatto.findById(id_piatto);
			prenotazione.getPiatti().add(piatto);
			dao_prenotazione.save(prenotazione);
			
			return "Ordinazione avvenuta con successo.";
		} 
		
		return "Mi dispiace, non esiste una prenotazione per questo tavolo [ TAVOLO n." + numero_tavolo + "]";
	}
	
	
	
	// 3) VISUALIZZA SCONTRINO & CONFERMA PAGAMENTO CONTO
	@GetMapping("/getConto")
	public String getConto(@RequestParam int numero_tavolo, String conferma) {
		
		// VARIABILI GLOBALI
		Prenotazione prenotazione = null;
		DtoConto dto_conto = null;	
		Conto conto = null;
		double totale = 0;
		
		// BUSINESS LOGIC
		prenotazione = dao_prenotazione.findByIdTavoloNative(numero_tavolo);
		if(prenotazione != null) {
			// CREAZIONE DELL'OGGETTO DTO 
			dto_conto = new DtoConto();
			dto_conto.setNumero_tavolo(numero_tavolo);
			dto_conto.setPiatti_ordinati( dao_conto.getPiattiOrdinatiByIdPrenotazioneNative(prenotazione.getId()) );
			dto_conto.setPrezzi_piatti( dao_conto.getPrezziPiattiByIdPrenotazioneNative(prenotazione.getId()) );
			dto_conto.setQuantita( dao_conto.getQuantitaPiattiByIdPrenotazioneNative(prenotazione.getId()) );
			totale = serviceTavolo.cassa( dto_conto.getPrezzi_piatti() );
			dto_conto.setTotale_complessivo(totale); 
			
			if(conferma.toUpperCase().equals("SI")) { // SE SI VUOLE CONFERMARE IL PAGAMENTO DEL CONTO
				conto = new Conto();
				conto.setTavolo(prenotazione.getTavolo());
				conto.setTotale(totale);
				// IL CONTO E' STATO PAGATO, SETTARE PAGATO = TRUE E OCCUPATO=FALSE PERCHE' IL TAVOLO SI E' LIBERATO 
				prenotazione.setPagato(true);
				serviceTavolo.cambioOccupazione(prenotazione.getTavolo());
				dao_conto.save(conto);
				dao_prenotazione.save(prenotazione);
				
				return "Pagamento avvenuto con successo !";
			}
				
			return "DETTAGLI CONTO:\n" + dto_conto;
		}
		
		return "Non è stato trovato alcun conto associato al tavolo [ TAVOLO n." + numero_tavolo + "]";
	}

}