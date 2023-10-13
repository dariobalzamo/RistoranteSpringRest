package com.ristorante.spring.service;

import java.util.List;
import com.ristorante.spring.model.*;


public interface IServiceTavolo {
	
	// DEFINIZIONE METODI DA IMPLEMENTARE IN SERVICE
	
	public int assegnazioneTavolo(List<Tavolo> tavoli, int numero_persone);
	
	public void cambioOccupazione(Tavolo tavolo);
	
	public double cassa(List<Double> listPrezzi);
}
