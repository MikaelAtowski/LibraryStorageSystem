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

import com.example.biblioteca.entity.Usuario;
import com.example.biblioteca.service.UsuarioServiceImpl;

@RestController
@RequestMapping("/usuarios/*")
@CrossOrigin(value = "http://localhost:4200")
public class UsuarioController {
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll(){
		return ResponseEntity.ok(usuarioServiceImpl.getAll());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id){
		Optional<Usuario> optionalUsuario = usuarioServiceImpl.getById(id);
		if(optionalUsuario.isPresent()) {
			return ResponseEntity.ok(optionalUsuario.orElseThrow());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Usuario> create(@RequestBody Usuario ususario){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServiceImpl.save(ususario));
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario, @PathVariable Long id){
		Optional<Usuario> optionalUsuario = usuarioServiceImpl.getById(id);
		if(optionalUsuario.isPresent()) {
			Usuario usuarioDb = optionalUsuario.orElseThrow();
			usuarioDb.setNombre(usuario.getNombre());
			usuarioDb.setApellido(usuario.getApellido());
			usuarioDb.setEmail(usuario.getEmail());
			usuarioDb.setTelefono(usuario.getTelefono());
			return ResponseEntity.status(HttpStatus.CREATED).body(usuarioServiceImpl.save(usuarioDb));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Usuario> delete(@PathVariable Long id){
		Optional<Usuario> optionalUsuario = usuarioServiceImpl.getById(id);
		if(optionalUsuario.isPresent()) {
			Usuario usuarioDeleted = usuarioServiceImpl.deleteById(id).orElseThrow();
			return ResponseEntity.status(HttpStatus.OK).body(usuarioDeleted);
		}
		return ResponseEntity.notFound().build();
	}	
}
