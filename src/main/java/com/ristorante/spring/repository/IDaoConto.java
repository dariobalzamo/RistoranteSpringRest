package com.ristorante.spring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ristorante.spring.model.*;
import java.util.List;

@Repository
public interface IDaoConto extends JpaRepository<Conto, Integer> {

//    @Query(nativeQuery = true, value = "SELECT *, COUNT(*) AS quantita, SUM(prezzo) AS totale FROM v_scontrino WHERE numero_prenotazione = ?1 AND pagato = false GROUP BY piatto")
//    List<Conto> findByIdPrenotazioneNative(int numero_prenotazione);
    
    @Query(nativeQuery = true, value = "select piatto FROM v_scontrino WHERE numero_prenotazione = ?1 AND pagato = false;")
    List<String> getPiattiOrdinatiByIdPrenotazioneNative(int numero_prenotazione);
    
    @Query(nativeQuery = true, value = "SELECT COUNT(*) AS quantita FROM v_scontrino WHERE numero_prenotazione = ?1 AND pagato = false GROUP BY piatto;")
    List<Integer> getQuantitaPiattiByIdPrenotazioneNative(int numero_prenotazione);
    
    @Query(nativeQuery = true, value = "SELECT round(sum(prezzo)) as totale FROM v_scontrino WHERE numero_prenotazione = ?1 AND pagato = false GROUP BY piatto;")
    List<Double> getPrezziPiattiByIdPrenotazioneNative(int numero_prenotazione);
}