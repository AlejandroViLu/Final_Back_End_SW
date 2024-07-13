package com.finalSW.CRUD.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.finalSW.CRUD.entidad.Detalles;

public interface DetallesInterface extends MongoRepository<Detalles, Integer> {
    List<Detalles> findByIdVenta(int idVenta);
}
