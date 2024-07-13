package com.finalSW.CRUD.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalSW.CRUD.entidad.Ventas;

public interface VentasInterface extends MongoRepository<Ventas, Integer>{

}
