package com.finalSW.Security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.finalSW.Security.entity.UserEntity;
import com.finalSW.Security.repository.UserEntityRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	UserEntityRepository r;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> userEntity = r.findByUsernameOrEmail(username, username);
		if(!userEntity.isPresent()) {
			throw new UsernameNotFoundException("No Existe");
		}
		return UserPrincipal.builder(userEntity.get());
	}
	
}
