package com.example.godzillaFilmes;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.godzillaFilmes.filters.JWTValidarFilter;
import com.example.godzillaFilmes.filters.TokenAuthenticationFilter;
import com.example.godzillaFilmes.services.AuthenticationService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final AuthenticationService service;
	private final PasswordEncoder passwordEncoder;

	public SecurityConfig(AuthenticationService service, PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
		this.service = service;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(passwordEncoder);
	}

	private static final String[] PUBLIC_MATCHERS_GET = { "/godzilla/**" };
	private static final String[] PUBLIC_MATCHERS = { "/clientes/**" };
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
				.antMatchers(HttpMethod.POST,PUBLIC_MATCHERS ).permitAll().anyRequest().authenticated().and()
				.addFilter(new TokenAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTValidarFilter(authenticationManager())).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

		CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}

}
