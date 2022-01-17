package com.example.godzillaFilmes.repositories;

import java.util.Optional;

import com.example.godzillaFilmes.domain.Cliente;

public interface ClienteRepositoryPort {
	
Optional<Cliente> findById(Integer id);
	
	Optional<Cliente> findByEmail(String email);
	
	Cliente save(Cliente user);

}
