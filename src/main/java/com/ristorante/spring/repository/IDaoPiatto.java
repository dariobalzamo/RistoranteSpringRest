package com.ristorante.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ristorante.spring.model.Piatto;

@Repository
public interface IDaoPiatto extends JpaRepository<Piatto, Integer> {

	public Piatto findById(int id);
}