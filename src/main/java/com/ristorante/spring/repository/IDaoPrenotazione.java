package com.ristorante.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ristorante.spring.model.Prenotazione;

@Repository
public interface IDaoPrenotazione extends JpaRepository<Prenotazione, Integer>{
	
	// Query JPQL (Java Persistance Query Lenguage): tipologia di query orientata agli oggetti, 
	// ovvero, puntano alle classi e non alle tabelle dei db relazionali. 
	// @Query("From Prenotazione where id_tavolo = ?1 and pagato = false")
	// public Prenotazione findByIdTavolo(int id_tavolo);
	
	// Native Query: query sql classiche, i dati vengono letti da un db relazionale.
	@Query(nativeQuery = true, value = "Select * from prenotazioni where id_tavolo = ?1 and pagato = false")
	Prenotazione findByIdTavoloNative(int id_tavolo);
	
}
