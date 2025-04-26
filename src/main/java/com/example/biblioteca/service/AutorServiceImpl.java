package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.biblioteca.entity.Autor;
import com.example.biblioteca.repository.AutorRepository;

@Service
public class AutorServiceImpl implements IService<Autor> {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Autor> getAll(){
		return (List<Autor>) autorRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Autor> getById(Long idAutor){
		return autorRepository.findById(idAutor);
	}
	
	@Transactional
	@Override
	public Autor save(Autor item) {
		return autorRepository.save(item);
	}
	
	@Transactional
	@Override
	public Optional<Autor> deleteById(Long id){
		Optional<Autor> autorOptional = autorRepository.findById(id);
		if(autorOptional.isPresent()) {
			autorRepository.deleteById(id);
			return autorOptional;
		}
		return null;
	}
}
