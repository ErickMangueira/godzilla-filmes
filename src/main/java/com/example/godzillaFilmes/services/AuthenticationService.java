package com.example.godzillaFilmes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.godzillaFilmes.domain.Cliente;
import com.example.godzillaFilmes.repositories.ClienteRepositoryPort;

public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private ClienteRepositoryPort repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Cliente> optional = repository.findByEmail(username);
		
		if(optional.isPresent()) {
			return optional.get();
		}
		
		throw new UsernameNotFoundException("User not found");
	}	

}
