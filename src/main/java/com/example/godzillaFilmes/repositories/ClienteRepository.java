package com.example.godzillaFilmes.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.godzillaFilmes.domain.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer>{
	

	
	Optional<Cliente> findByEmail(String email);

}
