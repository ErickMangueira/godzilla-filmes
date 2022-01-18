package com.example.godzillaFilmes.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



public class Perfil implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	
	private final Optional<Cliente> cliente;
	
	public Perfil(Optional<Cliente> cliente ) {
		this.cliente = cliente;
	}
	


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
	
    }
    
	@Override
	public String getPassword() {
		
		return cliente.orElse(new Cliente()).getPassword();
	}

	@Override
	public String getUsername() {
		return cliente.orElse(new Cliente()).getLogin();
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
