package com.finalSW.Security.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.finalSW.Security.entity.UserEntity;

@SuppressWarnings("serial")
public class UserPrincipal implements UserDetails{
	private String username;
	private String email;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserPrincipal(String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}
	
	public static UserPrincipal builder(UserEntity a) {
		Collection<GrantedAuthority> authorities = 
				a.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.name())).collect(Collectors.toList());
		return new UserPrincipal(a.getUsername(), a.getEmail(), a.getPassword(), authorities);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
}
