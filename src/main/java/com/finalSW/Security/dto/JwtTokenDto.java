package com.finalSW.Security.dto;

public class JwtTokenDto {
	public String token;
	
	public JwtTokenDto() {}

	public JwtTokenDto(String token) {
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
