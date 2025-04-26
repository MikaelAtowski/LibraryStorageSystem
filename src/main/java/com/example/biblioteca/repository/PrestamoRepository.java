package com.example.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.biblioteca.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
	
}
