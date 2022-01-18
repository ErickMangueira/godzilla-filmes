package com.example.godzillaFilmes.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.godzillaFilmes.domain.Cliente;
import com.example.godzillaFilmes.domain.Perfil;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TokenAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Value("${jwt.expiration}")
	 public static String TOKEN_EXPIRACAO;
	
	@Value("${jwt.secret}")
	    public static  String TOKEN_SENHA;

	    private final AuthenticationManager authenticationManager;

	    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
	        this.authenticationManager = authenticationManager;
	    }


	    @Override
	    public Authentication attemptAuthentication(HttpServletRequest request,
	                                                HttpServletResponse response) throws AuthenticationException {
	        try {
	            Cliente usuario = new ObjectMapper()
	                    .readValue(request.getInputStream(), Cliente.class);

	            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
	                    usuario.getEmail(),
	                    usuario.getPassword(),
	                    new ArrayList<>()
	            ));

	        } catch (IOException e) {
	            throw new RuntimeException("Falha ao autenticar usuario", e);
	        }

	    }

	    @Override
	    protected void successfulAuthentication(HttpServletRequest request,
	                                            HttpServletResponse response,
	                                            FilterChain chain,
	                                            Authentication authResult) throws IOException, ServletException {

	        Perfil usuarioData = (Perfil) authResult.getPrincipal();

	        String token = JWT.create().
	                withSubject(usuarioData.getUsername())
	                .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
	                .sign(Algorithm.HMAC512(TOKEN_SENHA));

	        response.getWriter().write(token);
	        response.getWriter().flush();
	    }
	}
