package com.example.biblioteca.exception;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(String resourceName) {
		super(resourceName + "no encontrado");
	}
}
