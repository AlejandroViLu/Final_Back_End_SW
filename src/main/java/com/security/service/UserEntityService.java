package com.security.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalSW.exceptions.AtributteException;
import com.security.dto.CreateUserDto;
import com.security.entity.UserEntity;
import com.security.enums.RoleEnum;
import com.security.repository.UserEntityRepository;

@Service
public class UserEntityService {
	@Autowired
	UserEntityRepository r;
	
	public UserEntity create(CreateUserDto u) throws AtributteException {
		if(r.existsByUsername(u.getUsername()))
			throw new AtributteException("Username ya en uso");
		if(r.existsByEmail(u.getEmail()))
			throw new AtributteException("Email ya en uso");
		int id = autoIncrement();
		
		List<RoleEnum> roles = u.getRoles().stream().map(rol -> RoleEnum.valueOf(rol)).collect(Collectors.toList());
		UserEntity userentity = new UserEntity(id, u.getUsername(), u.getEmail(), u.getPassword(), roles);
		return r.save(userentity);
	}
	private int autoIncrement() {
		List<UserEntity> lista = r.findAll();
		return lista.isEmpty()? 1:
			lista.stream().max(java.util.Comparator.comparing(UserEntity::getId)).get().getId() + 1;
	}
}
