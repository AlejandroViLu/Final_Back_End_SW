package com.finalSW.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.finalSW.entidad.Producto;

@Repository
public interface ProductoInterface extends MongoRepository<Producto, Integer>{
	boolean existsByNombre(String nombre);
	Optional<Producto> findByNombre(String nombre);
}
