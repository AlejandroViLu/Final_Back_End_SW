package com.finalSW.CRUD.repository;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.finalSW.CRUD.entidad.Categoria;


@Repository
public interface CategoriaInterface extends MongoRepository<Categoria, Integer> {
	boolean existsByNombre(String nombre);
	Optional<Categoria> findByNombre(String nombre);
}
