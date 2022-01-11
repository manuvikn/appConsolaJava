package com.manuvikn.proyecto.repository;

import java.util.List;

public interface Repository<T> {
	
	List<T> findAll();
	
	T getById(long id);
	
	long create(T t);
	
	long update(T t);
	
	long remove(long i);
	
}
