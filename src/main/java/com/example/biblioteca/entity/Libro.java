package com.example.biblioteca.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name =  "LIBRO")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LIBRO_SEQ")
	@SequenceGenerator(name = "LIBRO_SEQ", sequenceName = "LIBRO_SEQ", allocationSize = 1)
	@Column(name = "ID_LIBRO")
	private Long idLibro;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "ISBN")
	private String isbn;
	
	@Column(name = "STOCK")
	private Integer stock;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AUTOR_ID")
	private Autor autorId;

	public Long getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(Long idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Autor getAutorId() {
		return autorId;
	}

	public void setAutorId(Autor autorId) {
		this.autorId = autorId;
	}
	
}
