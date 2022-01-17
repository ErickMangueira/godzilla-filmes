package com.example.godzillaFilmes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.godzillaFilmes.domain.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, String>{

	Filme findByTituloIgnoreCaseContaining(String titulo);
	List<Filme> findByTituloContaining(String titulo);

}
