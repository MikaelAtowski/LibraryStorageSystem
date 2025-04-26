package com.example.biblioteca.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

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
@Table(name = "PRESTAMO")
public class Prestamo {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRESTAMO_SEQ")
	@SequenceGenerator(name = "PRESTAMO_SEQ", sequenceName = "PRESTAMO_SEQ", allocationSize = 1)
	
	@Column(name = "ID_PRESTAMO")
	private Long idPrestamo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USUARIO_ID")
	private Usuario usuarioId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "LIBRO_ID")
	private Libro libroId;
	
	@Column(name="FECHA_PRESTAMO")
	@JsonFormat(shape=Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaPrestamo;
	
	@Column(name="FECHA_DEVOLUCION")
	@JsonFormat(shape=Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaDevolucion;
	
	@Column(name="DEVUELTO")
	private Boolean devuelto;

	public Long getIdPrestamo() {
		return idPrestamo;
	}

	public void setIdPrestamo(Long idPrestamo) {
		this.idPrestamo = idPrestamo;
	}

	public Usuario getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Usuario usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Libro getLibroId() {
		return libroId;
	}

	public void setLibroId(Libro libroId) {
		this.libroId = libroId;
	}

	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}

	public void setFechaPrestamo(LocalDate fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(LocalDate fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Boolean getDevuelto() {
		return devuelto;
	}

	public void setDevuelto(Boolean devuelto) {
		this.devuelto = devuelto;
	}

	
}
