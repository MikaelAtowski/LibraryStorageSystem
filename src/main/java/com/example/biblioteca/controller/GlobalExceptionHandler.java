package com.example.biblioteca.controller;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DataIntegrityViolationException.class)
	// Extrae la causa para identificar la violación específica
	public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex){
		Throwable cause = ex.getRootCause();
		if(cause != null && cause.getMessage() != null) {
			return ResponseEntity.badRequest().body("Error de integridad: " + cause.getMessage());
		}
		return ResponseEntity.badRequest().body("Error de integridad de datos.");
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex){
		return ResponseEntity.badRequest().body("Violación de restriccion: " + ex.getMessage());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex){
		return ResponseEntity.internalServerError().body("Error: " + ex.getMessage());
	}
}
