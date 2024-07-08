package com.finalSW.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.finalSW.entidad.Usuario;
@Repository
public interface UsuarioInterface extends MongoRepository<Usuario, Integer>{
	boolean existsByusername(String username);
	Optional<Usuario> findByusername(String username);
}
