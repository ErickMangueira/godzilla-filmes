package com.example.godzillaFilmes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.godzillaFilmes.domain.Cliente;
import com.example.godzillaFilmes.domain.Perfil;
import com.example.godzillaFilmes.repositories.ClienteRepository;

@Service
public class AuthenticationService implements UserDetailsService{
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Cliente> cliente = repository.findByEmail(username);
		
		if(cliente.isEmpty()) {
			
			throw new UsernameNotFoundException("User not found");
		}
		
		return new Perfil(cliente);
	}	

}
