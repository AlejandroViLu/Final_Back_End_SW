package com.finalSW.Security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.CRUD.exceptions.MessageDto;
import com.finalSW.Security.dto.CreateUserDto;
import com.finalSW.Security.dto.JwtTokenDto;
import com.finalSW.Security.dto.LoginUserDto;
import com.finalSW.Security.entity.UserEntity;
import com.finalSW.Security.service.UserEntityService;


@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	@Autowired
	UserEntityService r;
	
	@PostMapping("/create")
	public ResponseEntity<MessageDto> create(@Valid @RequestBody CreateUserDto entity) throws AtributteException {
		UserEntity user = r.create(entity);
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Usuario "+user.getUsername()+" ha sido agregado."));
	}
	@PostMapping("/create-admin")
	public ResponseEntity<MessageDto> createAdmin(@Valid @RequestBody CreateUserDto entity) throws AtributteException {
		UserEntity user = r.createAdmin(entity);
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Administrador "+user.getUsername()+" ha sido agregado."));
	}
	@PostMapping("/create-user")
	public ResponseEntity<MessageDto> createUser(@Valid @RequestBody CreateUserDto entity) throws AtributteException {
		UserEntity user = r.createUser(entity);
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Cliente "+user.getUsername()+" ha sido agregado."));
	}
	@PostMapping("/login")
	public ResponseEntity<JwtTokenDto> login(@Valid @RequestBody LoginUserDto entity) throws AtributteException {
		JwtTokenDto jtd = r.login(entity); 
		return ResponseEntity.ok(jtd);
	}

}
