package com.ristorante.spring.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "prenotazioni")
public class Prenotazione {

	// ATTRIBUTI --> COLONNE TABELLA 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false)
	private boolean pagato;
	
	/*
	 * RELAZIONE PRENOTAZIONE (M) -- (1) TAVOLO
	 * ASSEGNO NELLA TABELLA "prenotazioni" la FK id_tavolo
	 */
	@ManyToOne
	@JoinColumn(name = "id_tavolo", referencedColumnName = "id")
	private Tavolo tavolo;
	
	/*
	 * RELAZIONE: PRENOTAZIONI (M) -- (M) PIATTI
	 * Una prenotazione associata ad un tavolo può essere relazionata a molti piatti da ordinare.
	 * Un Piatto, invece, può essere ordinato da molti tavoli, quindi a molte prenotazioni. 
	 * Si crea una tabella di cross chiamata Ordinazioni, avente id_piatto e id_prenotazione. 
	 */
	
	// Per creare una tabella di cross si utilizza l'annotazione JPA @JoinTable che accetta 3 attributi:
	// name: nome da dare alla tabella di cross.
	// joinColumns: definire la chiave esterna della classe (tabella) in cui ci troviamo.
	// inverseJoinColumns: indicare la chiave esterna relazionata.
	@ManyToMany
	@JoinTable(name = "ordinazioni", 
			   joinColumns = @JoinColumn(name = "id_prenotazione"),
			   inverseJoinColumns = @JoinColumn(name = "id_piatto") )
	private List<Piatto> piatti = new ArrayList<Piatto>();
	
	
	// METODI: GETTER & SETTER
	
	public List<Piatto> getPiatti() {
		return piatti;
	}

	public void setPiatti(List<Piatto> piatti) {
		this.piatti = piatti;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isPagato() {
		return pagato;
	}

	public void setPagato(boolean pagato) {
		this.pagato = pagato;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	@Override
	public String toString() {
		return "Prenotazione [id=" + id + ", pagato=" + pagato + ", tavolo=" + tavolo + ", piatti=" + piatti + "]";
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, pagato, piatti, tavolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prenotazione other = (Prenotazione) obj;
		return id == other.id && pagato == other.pagato && Objects.equals(piatti, other.piatti)
				&& Objects.equals(tavolo, other.tavolo);
	}
	
	public Prenotazione() {
		
	}

	public Prenotazione(boolean pagato, Tavolo tavolo, List<Piatto> piatti) {
		super();
		this.pagato = pagato;
		this.tavolo = tavolo;
		this.piatti = piatti;
	}
}