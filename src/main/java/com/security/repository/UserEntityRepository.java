package com.security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.security.entity.UserEntity;

@Repository
public interface UserEntityRepository extends MongoRepository<UserEntity, Integer>{
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
