package com.finalSW.Security.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.finalSW.Security.entity.UserEntity;

@Repository
public interface UserEntityRepository extends MongoRepository<UserEntity, Integer>{
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
	Optional<UserEntity> findByUsernameOrEmail(String username, String email);
}
