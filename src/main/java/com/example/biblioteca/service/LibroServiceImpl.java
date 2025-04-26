package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.biblioteca.dto.LibroDTO;
import com.example.biblioteca.entity.Autor;
import com.example.biblioteca.entity.Libro;
import com.example.biblioteca.repository.AutorRepository;
import com.example.biblioteca.repository.LibroRepository;

@Service
public class LibroServiceImpl implements IService<Libro> {
	@Autowired
	private LibroRepository libroRepository;
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Libro> getAll() {
		return (List<Libro>) libroRepository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Libro> getById(Long id) {
		return libroRepository.findById(id);
	}
	
	@Override
	@Transactional
	public Libro save(Libro item) {
		return libroRepository.save(item);
	}
	
	@Transactional
	public Libro save(LibroDTO item) {
		Autor autor  = autorRepository.findById(item.getAutorId()).orElseThrow(() -> new RuntimeException("Autor no Encontrado!"));
		Libro libro = new Libro();
		libro.setTitulo(item.getTitulo());
		libro.setIsbn(item.getIsbn());
		libro.setStock(item.getStock());
		libro.setAutorId(autor);
		return libroRepository.save(libro);
	}
	
	@Transactional
	public Libro update(LibroDTO libro,Long id) {
		Optional<Libro> optionalLibro = libroRepository.findById(id);
		if(optionalLibro.isPresent()) {
			Autor autor = autorRepository.findById(libro.getAutorId()).orElseThrow(() -> new RuntimeException("Autor no encontrado!"));
			Libro libroDb = optionalLibro.get();
			libroDb.setIdLibro(id);
			libroDb.setTitulo(libro.getTitulo());
			libroDb.setIsbn(libro.getIsbn());
			libroDb.setStock(libro.getStock());
			libroDb.setAutorId(autor);
			return libroRepository.save(libroDb);
		}
		return null;
	}
	
	@Override
	@Transactional
	public Optional<Libro> deleteById(Long id){
		Optional<Libro> libroOptional = libroRepository.findById(id);
		if (libroOptional.isPresent()) {
			libroRepository.deleteById(id);
			return libroOptional;
		}
		return null;
	}
}
