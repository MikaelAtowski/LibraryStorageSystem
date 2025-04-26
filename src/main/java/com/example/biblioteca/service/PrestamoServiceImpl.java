package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.biblioteca.dto.PrestamoDTO;
import com.example.biblioteca.entity.Libro;
import com.example.biblioteca.entity.Prestamo;
import com.example.biblioteca.entity.Usuario;
import com.example.biblioteca.exception.ResourceNotFoundException;
import com.example.biblioteca.repository.LibroRepository;
import com.example.biblioteca.repository.PrestamoRepository;
import com.example.biblioteca.repository.UsuarioRepository;

@Service
public class PrestamoServiceImpl implements IService<Prestamo> {
	
	@Autowired
	private PrestamoRepository prestamoRepository;
	
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Prestamo> getAll(){
		return (List<Prestamo>) prestamoRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Prestamo> getById(Long id){
		return prestamoRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Prestamo save(Prestamo item) {
		return prestamoRepository.save(item);
	}
	
	@Transactional
	public Prestamo save(PrestamoDTO item) {
		Usuario usuario = usuarioRepository.findById(item.getUsuarioId()).orElseThrow(() -> new ResourceNotFoundException("Usuario"));
		Libro libro = libroRepository.findById(item.getLibroId()).orElseThrow(() -> new ResourceNotFoundException("Usuario"));
		Prestamo prestamoDb = new Prestamo();
		prestamoDb.setUsuarioId(usuario);
		prestamoDb.setLibroId(libro);
		prestamoDb.setFechaPrestamo(item.getFechaPrestamo());
		prestamoDb.setFechaDevolucion(item.getFechaDevolucion());
		prestamoDb.setDevuelto(item.getDevuelto());
		return prestamoRepository.save(prestamoDb);
	}
	
	@Transactional
	public Prestamo update(PrestamoDTO item, Long id) {
		Optional<Prestamo> optionalPrestamo = prestamoRepository.findById(id);
		if(optionalPrestamo.isPresent()) {
			Usuario usuario = usuarioRepository.findById(item.getUsuarioId()).orElseThrow(() -> new ResourceNotFoundException("Usuario"));
			Libro libro = libroRepository.findById(item.getLibroId()).orElseThrow(() -> new ResourceNotFoundException("Libro"));
			Prestamo prestamoDb = optionalPrestamo.get();
			prestamoDb.setUsuarioId(usuario);
			prestamoDb.setLibroId(libro);
			prestamoDb.setFechaPrestamo(item.getFechaPrestamo());
			prestamoDb.setFechaDevolucion(item.getFechaDevolucion());
			prestamoDb.setDevuelto(item.getDevuelto());
			return prestamoRepository.save(prestamoDb);
		}
		return null;
	}
	
	@Transactional
	public Optional<Prestamo> deleteById(Long id){
		Optional<Prestamo> prestamoOptional = prestamoRepository.findById(id);
		if(prestamoOptional.isPresent()) {
			prestamoRepository.deleteById(id);
			return prestamoOptional;
		}
		return null;
	}
}
