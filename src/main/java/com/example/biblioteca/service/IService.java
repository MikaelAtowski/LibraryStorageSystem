package com.example.biblioteca.service;

import java.util.List;
import java.util.Optional;

public interface IService <T>{
	List<T> getAll();
	Optional<T> getById(Long id);
	T save (T item);
	Optional<T> deleteById(Long id);
}
