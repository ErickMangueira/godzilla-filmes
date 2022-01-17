package com.example.godzillaFilmes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.godzillaFilmes.filters.TokenAuthenticationFilter;
import com.example.godzillaFilmes.repositories.ClienteRepositoryPort;
import com.example.godzillaFilmes.services.AuthenticationService;
import com.example.godzillaFilmes.services.TokenService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private ClienteRepositoryPort repository;
	
	
	@Override
    protected void configure(AuthenticationManagerBuilder cliente) throws Exception {
    	cliente.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
    }

	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
		

	private static final String[] PUBLIC_MATCHERS_GET = { "/godzilla/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		
		http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
		.antMatchers(HttpMethod.POST, "/cliente").permitAll()
		.anyRequest().authenticated().and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new TokenAuthenticationFilter(tokenService, repository), UsernamePasswordAuthenticationFilter.class);
	}

	

}
