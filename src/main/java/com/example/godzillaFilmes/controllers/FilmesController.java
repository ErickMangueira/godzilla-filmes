package com.example.godzillaFilmes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.godzillaFilmes.domain.Filme;
import com.example.godzillaFilmes.services.FilmeService;


@RestController
@RequestMapping
public class FilmesController {
	
	@Autowired
	private FilmeService service;
	
	
	@RequestMapping(value = "/godzilla/{titulo}", method = RequestMethod.GET)
	public ResponseEntity<Filme> findByTituloIgnoreCaseContaining(@PathVariable String titulo) {
		
		Filme obj = service.findByTituloIgnoreCaseContaining(titulo);
		 
		if(obj.getEstoque() > 0 && obj!= null) {
		return ResponseEntity.ok().body(obj);
		}
		return new ResponseEntity<>(null,new HttpHeaders(), HttpStatus.FORBIDDEN);
	}
	
	
	@RequestMapping(value = "/{titulo}", method = RequestMethod.GET)
	public ResponseEntity<List<Filme>> findByTituloContaining(@PathVariable String titulo) {
		List<Filme> list = service.findByTituloContaining(titulo);
		return ResponseEntity.ok().body(list);

	}

}
