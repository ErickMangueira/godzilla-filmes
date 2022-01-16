package com.example.godzillaFilmes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Filme {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer filmesId;

	private String titulo;

	private String diretor;
	
	
	private Integer estoque;


	public Integer getFilmesId() {
		return filmesId;
	}


	public void setFilmesId(Integer filmesId) {
		this.filmesId = filmesId;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDiretor() {
		return diretor;
	}


	public void setDiretor(String diretor) {
		this.diretor = diretor;
	}


	public Integer getEstoque() {
		return estoque;
	}


	public void setEstoque(Integer estoque) {
		this.estoque = estoque;
	}
	
	

}
