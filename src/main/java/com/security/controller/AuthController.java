package com.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalSW.exceptions.AtributteException;
import com.finalSW.exceptions.MessageDto;
import com.security.dto.CreateUserDto;
import com.security.entity.UserEntity;
import com.security.service.UserEntityService;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	UserEntityService r;
	
	@PostMapping("/create")
	public ResponseEntity<MessageDto> postMethodName(@RequestBody CreateUserDto entity) throws AtributteException {
		UserEntity user = r.create(entity);
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Usuario "+entity.getUsername()+" ha sido agregado."));
	}
	
}
