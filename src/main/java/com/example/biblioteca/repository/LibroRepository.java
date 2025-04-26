package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.entity.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

}
