package com.security.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.security.enums.RoleEnum;

@Document(collation = "users")
public class UserEntity {
	@Id
	private int id;
	private String username;
	private String email;
	private String password;
	private List<RoleEnum> roles;
	public UserEntity(int id, String username, String email, String password, List<RoleEnum> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<RoleEnum> getRoles() {
		return roles;
	}
	public void setRoles(List<RoleEnum> roles) {
		this.roles = roles;
	}
	
	
}
