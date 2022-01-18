package com.example.godzillaFilmes.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.godzillaFilmes.domain.Cliente;
import com.example.godzillaFilmes.repositories.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class AuthenticationController {
	
	
	private final ClienteRepository repository;
	
	
	private final PasswordEncoder encoder;
	
	public AuthenticationController(ClienteRepository repository, PasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;
	}
	
	@PostMapping("/cliente")
	public ResponseEntity<Cliente> cliente(@RequestBody  Cliente cliente){
		cliente.setPassword(encoder.encode(cliente.getPassword()));
		return ResponseEntity.ok(repository.save(cliente));
	}

}