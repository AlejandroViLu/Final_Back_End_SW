package com.finalSW.Security.dto;

import javax.validation.constraints.NotBlank;

public class LoginUserDto {
	@NotBlank(message = "Username Requerido")
	private String username;
	@NotBlank(message = "Password Requerido")
	private String password;
	public LoginUserDto(@NotBlank(message = "Username Requerido") String username,
			@NotBlank(message = "Password Requerido") String password) {
		this.username = username;
		this.password = password;
	}
	public LoginUserDto() {
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
