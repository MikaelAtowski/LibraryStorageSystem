package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
