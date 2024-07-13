package com.finalSW.Security.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.Security.dto.CreateUserDto;
import com.finalSW.Security.dto.JwtTokenDto;
import com.finalSW.Security.dto.LoginUserDto;
import com.finalSW.Security.entity.UserEntity;
import com.finalSW.Security.enums.RoleEnum;
import com.finalSW.Security.jwt.JwtProvider;
import com.finalSW.Security.repository.UserEntityRepository;
import com.finalSW.global.utils.Operations;

@Service
public class UserEntityService {
	@Autowired
	UserEntityRepository r;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JwtProvider jp;
	@Autowired
	AuthenticationManager am;
	
	public UserEntity create(CreateUserDto u) throws AtributteException {
		if(r.existsByUsername(u.getUsername()))
			throw new AtributteException("Username ya en uso");
		if(r.existsByEmail(u.getEmail()))
			throw new AtributteException("Email ya en uso");
		if(u.getRoles().isEmpty()) {
			throw new AtributteException("Roles obligatorios");
		}
		return r.save(mapUserFromDto(u));
	}
	
	public UserEntity createAdmin(CreateUserDto u) throws AtributteException {
		if(r.existsByUsername(u.getUsername()))
			throw new AtributteException("Username ya en uso");
		if(r.existsByEmail(u.getEmail()))
			throw new AtributteException("Email ya en uso");
		List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_USER");
		u.setRoles(roles);
		return r.save(mapUserFromDto(u));
	}
	
	public UserEntity createUser(CreateUserDto u) throws AtributteException {
		if(r.existsByUsername(u.getUsername()))
			throw new AtributteException("Username ya en uso");
		if(r.existsByEmail(u.getEmail()))
			throw new AtributteException("Email ya en uso");
		List<String> roles = Arrays.asList("ROLE_USER");
		u.setRoles(roles);
		return r.save(mapUserFromDto(u));
	}
	
	public JwtTokenDto login(LoginUserDto dto) {
		Authentication au = 
				am.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(au);
		String token = jp.generatedToken(au);
		return new JwtTokenDto(token);
				
	}
	
	private UserEntity mapUserFromDto(CreateUserDto d) {
		int id = Operations.autoIncrement(r.findAll());
		String password = passwordEncoder.encode(d.getPassword());
		List<RoleEnum> roles = 
				d.getRoles().stream().map(rol -> RoleEnum.valueOf(rol)).collect(Collectors.toList());
		return new UserEntity(id, d.getUsername(), d.getEmail(), password, roles);
	}
	
	
}
