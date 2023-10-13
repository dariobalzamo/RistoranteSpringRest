package com.ristorante.spring.model;

import javax.persistence.*;

@Entity
@Table(name = "conti")
public class Conto {

	// ATTRIBUTI 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;	
	private double totale;
	
	/* RELAZIONE:  
	 * CONTO (M) -- (1) TAVOLO 
	 * UN TAVOLO VIENE ASSOCIATO A MOLTI CONTI, SICCOME OGNI VOLTA CHE SI LIBERA VIENE EFFETTUATA UNA NUOVA PRENOTAZIONE DI UN NUOVO CLIENTE
	 */
	@ManyToOne
	@JoinColumn(name = "numero_tavolo", referencedColumnName = "id")
	private Tavolo tavolo;
	
	
	// METODI: GETTER & SETTER

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotale() {
		return totale;
	}

	public void setTotale(double totale) {
		this.totale = totale;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

}