package com.ristorante.spring.model;

import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
@Table(name = "piatti")
public class Piatto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(length = 50, nullable = false, unique = true)
	private String nome;
	
	@Column(scale = 6, precision = 2)
	private double prezzo;

	/*
	 * RELAZIONE: PIATTO (M) -- (M) PRENOTAZIONE
	 * Una prenotazione associata ad un tavolo può essere relazionata a molti piatti da ordinare.
	 * Un Piatto, invece, può essere ordinato da molti tavoli, quindi a molte prenotazioni. 
	 * Si crea una tabella di cross chiamata Ordinazioni, avente id_piatto e id_prenotazione. 
	 */
	@ManyToMany
	@JoinTable(name = "ordinazioni", 
			   joinColumns = @JoinColumn(name = "id_piatto"),
			   inverseJoinColumns = @JoinColumn(name = "id_prenotazione") )
	private List<Prenotazione> prenotazioni;
	
	
	// METODI: GETTER & SETTER
	
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

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	@Override
	public String toString() {
		return "Piatto [id=" + id + ", nome=" + nome + ", prezzo=" + prezzo + "]";
	}

	
	@Override
	public int hashCode() {
		return Objects.hash(id, nome, prezzo);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piatto other = (Piatto) obj;
		return id == other.id && Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(prezzo) == Double.doubleToLongBits(other.prezzo);
	}

	
	public Piatto(String nome, double prezzo) {
		super();
		this.nome = nome;
		this.prezzo = prezzo;
	}
	
	
	public Piatto() {
		
	}
	
}