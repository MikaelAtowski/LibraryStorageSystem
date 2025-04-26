package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long> {

}
