package com.finalSW.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.finalSW.entidad.Role;

public class UsuarioDto {
    @NotBlank(message = "Usuario correo obligatorio")
	private String username;
    
    @NotBlank(message = "Usuario apellido obligatorio")
	private String lastname;
	
    @NotBlank(message = "Usuario nombre obligatorio")
	private String firstname;
    	
    @NotBlank(message = "Usuario contraseña obligatorio")
	private String password;
   
    @NotNull(message = "Usuario telefono obligatorio")
    private int phone;
    
    @NotNull(message = "Usuario rol obligatorio")
    private Role rol;

	public UsuarioDto(String username,
			String lastname,
			String firstname,
			String password,
			int phone,
			Role rol) {
		this.username = username;
		this.lastname = lastname;
		this.firstname = firstname;
		this.password = password;
		this.phone = phone;
		this.rol = rol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public Role getRol() {
		return rol;
	}

	public void setRol(Role rol) {
		this.rol = rol;
	}
}
