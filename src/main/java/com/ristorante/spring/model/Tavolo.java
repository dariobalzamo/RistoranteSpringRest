package com.ristorante.spring.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

//Effettuo il Mapping delle classi Model, dette anche Entity, come tabelle di un database relazionale.

@Entity
@Table(name = "tavoli")   
public class Tavolo {
	
	// ################# ATTRIBUTI --> COLONNE DELLA TABELLA #################
	
	// Definisco il campo "id" come Primary key e Auto incrementale
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	
	@Column(nullable = false)
	private int numero_posti;
	
	@Column(nullable = false)
	private boolean occupato;
	
	// RELAZIONI
	@OneToMany(mappedBy = "tavolo") // "mappedBy" crea il vincolo di integrità referenziale: collegando il riferimento di una FK alla colonna id;
	private List<Prenotazione> prenotazioni;
	@OneToMany(mappedBy = "tavolo")
	private List<Conto> conti;
	
	// ################# METODI GETTER - SETTER #################
	
	public List<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	public void setPrenotazioni(List<Prenotazione> prenotazioni) {
		this.prenotazioni = prenotazioni;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumero_posti() {
		return numero_posti;
	}

	public void setNumero_posti(int numero_posti) {
		this.numero_posti = numero_posti;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}
	

	@Override
	public String toString() {
		return "Tavolo [id=" + id + ", numero_posti=" + numero_posti + ", occupato=" + occupato + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, numero_posti, occupato);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tavolo other = (Tavolo) obj;
		return id == other.id && numero_posti == other.numero_posti && occupato == other.occupato;
	}
	
	public Tavolo() {
		
	}
	
	public Tavolo(int numero_posti, boolean occupato) {
		super();
		this.numero_posti = numero_posti;
		this.occupato = occupato;
	}
}