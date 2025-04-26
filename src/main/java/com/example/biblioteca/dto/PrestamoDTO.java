package com.example.biblioteca.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class PrestamoDTO {
	private Long idPrestamo;
	private Long usuarioId;
	private Long libroId;
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaPrestamo;
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	private LocalDate fechaDevolucion;
	private Boolean Devuelto;
	
	public Long getIdPrestamo() {
		return idPrestamo;
	}
	public void setIdPrestamo(Long idPrestamo) {
		this.idPrestamo = idPrestamo;
	}
	public Long getUsuarioId() {
		return usuarioId;
	}
	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}
	public Long getLibroId() {
		return libroId;
	}
	public void setLibroId(Long libroId) {
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
		return Devuelto;
	}
	public void setDevuelto(Boolean devuelto) {
		Devuelto = devuelto;
	}
	
}
