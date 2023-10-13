package com.ristorante.spring.dto;

import java.util.List;

//Un oggetto DTO (Data Transfer Object) contiene solo i campi indispensabili dei model. 
public class DtoConto { 
	
	// ATTRIBUTI 
	
	private int numero_tavolo;	
	private List<String> piatti_ordinati;
	private List<Double> prezzi_piatti;
	private List<Integer> quantita;
	private double totale_complessivo;
	
	
	// METODI: GETTER & SETTER
	
	public int getNumero_tavolo() {
		return numero_tavolo;
	}
	
	public void setNumero_tavolo(int numero_tavolo) {
		this.numero_tavolo = numero_tavolo;
	}
	
	public List<String> getPiatti_ordinati() {
		return piatti_ordinati;
	}
	
	public void setPiatti_ordinati(List<String> piatti_ordinati) {
		this.piatti_ordinati = piatti_ordinati;
	}
	
	public List<Double> getPrezzi_piatti() {
		return prezzi_piatti;
	}
	
	public void setPrezzi_piatti(List<Double> prezzi_piatti) {
		this.prezzi_piatti = prezzi_piatti;
	}
	
	public List<Integer> getQuantita() {
		return quantita;
	}
	
	public void setQuantita(List<Integer> quantita) {
		this.quantita = quantita;
	}

	
	public double getTotale_complessivo() {
		return totale_complessivo;
	}
	

	public void setTotale_complessivo(double totale_complessivo) {
		this.totale_complessivo = totale_complessivo;
	}

	@Override
	public String toString() {
		return "numero_tavolo=" + numero_tavolo + "\npiatti_ordinati=" + piatti_ordinati + "\nprezzi_piatti="
				+ prezzi_piatti + "\nquantita=" + quantita + "\ntotale_complessivo=" + totale_complessivo + " euro.";
	}
	
}