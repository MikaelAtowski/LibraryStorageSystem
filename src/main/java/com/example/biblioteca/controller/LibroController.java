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

import com.example.biblioteca.service.LibroServiceImpl;
import com.example.biblioteca.dto.LibroDTO;
import com.example.biblioteca.entity.Libro;

@RestController
@RequestMapping("/libros/*")
@CrossOrigin(value = "http://localhost:4200")
public class LibroController {
	@Autowired
	private LibroServiceImpl libroServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<Libro>> getAll(){
		return ResponseEntity.ok(libroServiceImpl.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Libro> getById(@PathVariable Long id){
		Optional<Libro> optionalLibro = libroServiceImpl.getById(id);
		if(optionalLibro.isPresent()) {
			return ResponseEntity.ok(optionalLibro.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody LibroDTO libro){
		if(libro.getAutorId() == null) {
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("El Id del autor no puede ser nulo");
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(libroServiceImpl.save(libro));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<?> update(@RequestBody LibroDTO libro, @PathVariable Long id){
		if(libro.getAutorId() == null) {
			StringBuilder errorMessage = new StringBuilder();
			errorMessage.append("El Id del Autor no puede ser nulo");
			return ResponseEntity.badRequest().body(errorMessage.toString());
		}
		
		Libro updateLibro = libroServiceImpl.update(libro, id);
		if(updateLibro != null) {
			return ResponseEntity.ok(updateLibro);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Libro> delete(@PathVariable Long id){
		Optional<Libro> optionalLibro = libroServiceImpl.getById(id);
		if(optionalLibro.isPresent()) {
			Libro libroDeleted = libroServiceImpl.deleteById(id).orElseThrow();
			return ResponseEntity.status(HttpStatus.OK).body(libroDeleted);
		}
		return ResponseEntity.notFound().build();
	}
	
}
