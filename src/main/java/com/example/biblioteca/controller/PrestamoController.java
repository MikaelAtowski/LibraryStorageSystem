package com.example.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.biblioteca.dto.PrestamoDTO;
import com.example.biblioteca.entity.Prestamo;
import com.example.biblioteca.service.PrestamoServiceImpl;

@RestController
@RequestMapping("/prestamos/*")
@CrossOrigin(value = "http://localhost:4200")
public class PrestamoController {
	@Autowired
	private PrestamoServiceImpl prestamoServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<Prestamo>> getAll(){
		return ResponseEntity.ok(prestamoServiceImpl.getAll());
	}

	@GetMapping("{id}")
	public ResponseEntity<Prestamo> geById(@PathVariable Long id){
		Optional<Prestamo> optionalPrestamo = prestamoServiceImpl.getById(id);
		if(optionalPrestamo.isPresent()) {
			return ResponseEntity.ok(optionalPrestamo.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody PrestamoDTO prestamo){
		StringBuilder errorMessage = new StringBuilder();
		
		if(prestamo.getUsuarioId() == null) {
			errorMessage.append("El Usuario no puede ser nulo.");
		}
		
		if(prestamo.getLibroId() == null) {
			errorMessage.append("El Libro no puede ser nulo.");
		}
		
		if(errorMessage.length() > 0) {
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(prestamoServiceImpl.save(prestamo));
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody PrestamoDTO prestamo, @PathVariable Long id){
		if(prestamo.getLibroId() == null) {
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("El ID del Libro no puede ser nulo");
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}
		
		if(prestamo.getUsuarioId() == null) {
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("El ID del Usuario no puede ser nulo");
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}
		
		Prestamo updatePrestamo = prestamoServiceImpl.update(prestamo, id);
		if (updatePrestamo != null) {
			return ResponseEntity.ok(updatePrestamo);
		}
			
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Prestamo> delete(@PathVariable Long id){
		Optional<Prestamo> optionalPrestamo = prestamoServiceImpl.getById(id);
		if(optionalPrestamo.isPresent()) {
			Prestamo prestamoDeleted = prestamoServiceImpl.deleteById(id).orElseThrow();
			return ResponseEntity.status(HttpStatus.OK).body(prestamoDeleted);
		}
		return ResponseEntity.notFound().build();
	}
}
