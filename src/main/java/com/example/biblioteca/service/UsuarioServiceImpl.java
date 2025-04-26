package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.biblioteca.entity.Usuario;
import com.example.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IService<Usuario> {
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<Usuario> getAll(){
		return (List<Usuario>) usuarioRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Optional<Usuario> getById(Long id){
		return usuarioRepository.findById(id);
	}
	
	@Transactional
	@Override
	public Usuario save(Usuario item) {
		return usuarioRepository.save(item);
	}
	
	@Transactional
	@Override
	public Optional<Usuario> deleteById(Long id){
		Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
		if(usuarioOptional.isPresent()) {
			usuarioRepository.deleteById(id);
			return usuarioOptional;
		}
		return null;
	}
}
