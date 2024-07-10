package com.finalSW.Security.dto;

import java.util.ArrayList;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CreateUserDto {
	@NotBlank(message = "Username Requerido")
	private String username;
	@NotBlank(message = "Email Requerido")
	@Email(message = "Email Invalido")
	private String email;
	@NotBlank(message = "Password Requerido")
	private String password;
	//@NotEmpty(message = "Roles son Requerido")
	private List<String> roles = new ArrayList<String>();
	
	public CreateUserDto() {
		
	}
	public CreateUserDto(String username, String email, String password, List<String> roles) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
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
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	
}
