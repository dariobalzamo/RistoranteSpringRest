package com.ristorante.spring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ristorante.spring.model.*;
import com.ristorante.spring.repository.*;


//CLASSE PER LA GESTIONE DELLE RICHIESTE HTTP ED ELABORAZIONE DELLE RISPOSTE IN FORMATO JSNO (Content-type: Application/Json)
@RestController 
@RequestMapping("/ristoratore")  // Controller per il microservizio ristoratore
public class MicroservizioRistoratore {

	
	// #################### VARIABILI GLOBALI ####################
	/* Dependency Injection: iniezione, nella variabile "dao_tavolo", 
	 * del Bean (oggetto di classe anonima) istanziato dal IoC container. 
	 */
	
	@Autowired 
	private IDaoTavolo dao_tavolo;
	
	@Autowired
	private IDaoPiatto dao_piatto;
	
	
	// #################### GESTIONE TAVOLO ####################
	/* Le servlet con Spring non esistono più, vengono sostituite con dei metodi 
	 * annotati con @PostMapping("/path") se la richiesta HTTP è di tipo POST, altrimenti se 
	 * la richiesta HTTP è di tipo GET sarà annotato con GetMapping("/path").
	 */
	
	
	// METODO DI INSERIMENTO 
	@PostMapping("/insertTavolo") // mapping del metodo di tipo POST come servlet, identificata con il seguente path: "/insertTavolo". 
	public String insertTavolo(@RequestParam int numero_posti, boolean occupato) // L'annotation @RequestParam sostituisce il request.getParameter("id"). Dunque, recupera i dati della richiesta.  
	{
		Tavolo tavolo;
		
		// Stiamo applicando il Data Binding esplicitamente: meccanismo automatico eseguito da Spring che consente di associare i dati della richiesta e convertirli in un oggetto.
		tavolo = new Tavolo();
		tavolo.setNumero_posti(numero_posti);
		tavolo.setOccupato(occupato);
		dao_tavolo.save(tavolo); // Inserimento dell'oggetto tavolo nel database ad oggetti e successivamente in quello relazionale. 
		
		return "Inserimento avvenuto con successo."; // In Spring il return rappresenta la respinse (sostituisce response.sendRedirect("home.jsp"); in Spring MVC con pagine HTML)
	}
	
	// METODO SIA DI INSERIMENTO CHE DI MODIFICA 
	@PostMapping("/upsertTavolo")
	public String upsertTavolo(Tavolo tavolo)  // Operazione di Data Binding: iniezione degli oggetti della richiesta (i dati della richiesta - json/querystring vengono convertiti in oggetti)
	{
		String response = "Inserimento avvenuto con successo."; 
		
		if(dao_tavolo.existsById(tavolo.getId())) // il metodo existsById è di tipo boolean, verifica se esiste un oggetto/record.
			response = "Modifica avvenuta con successo."; // Se l'oggetto tavolo ha un id significa che il metodo è per la modifica
		dao_tavolo.save(tavolo);
		
		return response; 
	}
	
	// READ ALL
	@GetMapping("/getTavoli")
	public List<Tavolo> getTavoli() {
		return dao_tavolo.findAll();
	}
	
	// METODO PER LA LETTURA DI UN OGGETTO
	@GetMapping("/getTavolo")
	public Tavolo getTavolo(@RequestParam int id) {
		return dao_tavolo.findById(id);
	}
	
	// METODO PER LA RIMOZIONE
	@GetMapping("/deleteTavolo")
	public String deleteTavolo(@RequestParam int id) {
		dao_tavolo.deleteById(id);
		return "Il tavolo n." + id + " è stato eliminato con successo.";
	}
	
	
	// ################## GESTIONE PIATTO ####################
	
	// INSERT/UPDATE
	@PostMapping("/upsertPiatto")
	public String upsertPiatto(Piatto piatto)  // Operazione di Data Binding: iniezione degli oggetti della richiesta (i dati della richiesta - json/querystring vengono convertiti in oggetti)
	{
		String response = "Inserimento avvenuto con successo."; 
		
		if(dao_piatto.existsById(piatto.getId())) // il metodo existsById è di tipo boolean, verifica se esiste un oggetto/record.
			response = "Modifica avvenuta con successo."; // Se l'oggetto tavolo ha un id significa che il metodo è per la modifica
		dao_piatto.save(piatto);
		
		return response; 
	}
	
	// READ
	@GetMapping("/getPiatto")
	public Piatto getPiatto(@RequestParam int id) {
		return dao_piatto.findById(id);
	}
		
	// READ ALL
	@GetMapping("/getPiatti")
	public List<Piatto> getPiatti() {
		return dao_piatto.findAll();
	}
	
	// DELETE
	@GetMapping("/deletePiatto")
	public String deletePiatto(@RequestParam int id) {
		dao_piatto.deleteById(id);
		return "Il piatto n." + id + " è stato eliminato con successo.";
	}
}