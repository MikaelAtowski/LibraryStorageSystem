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

import com.example.biblioteca.service.AutorServiceImpl;
import com.example.biblioteca.entity.Autor;

@RestController
@RequestMapping("/autores/*")
@CrossOrigin(value = "http://localhost:4200")
public class AutorController {
	
	@Autowired
	private AutorServiceImpl autorServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<Autor>> getAll(){
		return ResponseEntity.ok(autorServiceImpl.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Autor> getById(@PathVariable Long id){
		Optional<Autor> optionalAutor = autorServiceImpl.getById(id);
		if(optionalAutor.isPresent()) {
			return ResponseEntity.ok(optionalAutor.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Autor> create(@RequestBody Autor autor){
		return ResponseEntity.status(HttpStatus.CREATED).body(autorServiceImpl.save(autor));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Autor> update(@RequestBody Autor autor, @PathVariable Long id){
		Optional<Autor> optionalAutor = autorServiceImpl.getById(id);
		if(optionalAutor.isPresent()) {
			Autor autorDb = optionalAutor.orElseThrow();
			autorDb.setNombre(autor.getNombre());
			autorDb.setApellido(autor.getApellido());
			autorDb.setNacionalidad(autor.getNacionalidad());
			return ResponseEntity.status(HttpStatus.CREATED).body(autorServiceImpl.save(autorDb));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Autor> delete(@PathVariable Long id){
		Optional<Autor> optionalAutor = autorServiceImpl.getById(id);
		if(optionalAutor.isPresent()) {
			Autor autorDeleted = autorServiceImpl.deleteById(id).orElseThrow();
			return ResponseEntity.status(HttpStatus.OK).body(autorDeleted);
		}
		return ResponseEntity.notFound().build();
	}
}
