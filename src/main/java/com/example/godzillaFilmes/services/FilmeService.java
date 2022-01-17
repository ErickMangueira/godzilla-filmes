package com.example.godzillaFilmes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.godzillaFilmes.domain.Filme;
import com.example.godzillaFilmes.repositories.FilmeRepository;

@Service
public class FilmeService {

	@Autowired
	private FilmeRepository repo;

	public Filme findByTituloIgnoreCaseContaining(String titulo) {
		Filme obj = repo.findByTituloIgnoreCaseContaining(titulo);
		return obj;

	}

	public List<Filme> findByTituloContaining(String titulo) {
		return repo.findByTituloContaining(titulo);
	}
}