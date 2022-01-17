package com.example.godzillaFilmes.repositories;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.godzillaFilmes.domain.Cliente;

@Component
public class ClienteRepositoryAdapter implements ClienteRepositoryPort{
	
	@Autowired
	private ClienteRepository authRepository;

	@Override
	public Cliente save(Cliente cliente) {
		return authRepository.save(cliente);
	}

	@Override
	public Optional<Cliente> findByEmail(String email) {
		return authRepository.findByEmail(email);
	}
	
	@Override
	public Optional<Cliente> findById(Integer id){
		return authRepository.findById(id);
	}

}