package com.finalSW.Security.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.finalSW.Security.enums.RoleEnum;
import com.finalSW.global.entity.EntityId;

@Document(collection = "users")
public class UserEntity extends EntityId{
	private String username;
	private String email;
	private String password;
	private List<RoleEnum> roles;
	private List<ItemCarrito> carrito = new ArrayList<>();
	public UserEntity(int id, String username, String email, String password, List<RoleEnum> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public int getId() {
		return super.getId();
	}

	@Override
	public void setId(int id) {
		super.setId(id);
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

	public List<ItemCarrito> getCarrito() {
		return carrito;
	}

	public void setCarrito(List<ItemCarrito> carrito) {
		this.carrito = carrito;
	}
	
	
}
