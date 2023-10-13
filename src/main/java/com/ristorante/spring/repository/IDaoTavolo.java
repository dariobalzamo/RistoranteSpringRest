package com.ristorante.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ristorante.spring.model.Tavolo;


@Repository // Componente Repository: Spring crea un oggetto di classe anonima in quanto è un'interfaccia. 
public interface IDaoTavolo extends JpaRepository<Tavolo, Integer>{
	
	// Ereditiamo da JpaRepository, in particolare da CrudRepository, i metodi CRUD che sono già configurati in automatico da framework
	// I metodi CRUD messi a disposizione dall'interfaccia CrudRepository sono: 
	// 1) save() --> inserimnento e modifica;
	// 2) findByAll() --> lettura dei dati; 
	// 3) delete() --> rimozione; 
	// 4) existsById() --> se esiste un oggetto 
	
	// QueryMethod: creazione di metodi personalizzati (tipicamente quelli di ricerca) sfruttando le keyword messe a disposizione dal modulo Spring Data
	public Tavolo findById(int id);
	
}
