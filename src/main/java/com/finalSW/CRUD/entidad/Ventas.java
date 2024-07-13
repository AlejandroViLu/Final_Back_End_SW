package com.finalSW.CRUD.entidad;

import org.springframework.data.mongodb.core.mapping.Document;

import com.finalSW.global.entity.EntityId;

@Document(collection = "Ventas")
public class Ventas extends EntityId{
	private String username;
	private String fecha;
	private double total;
	private String estado;
	public Ventas(int id, String username, String fecha, double total, String estado) {
		this.id = id;
		this.username = username;
		this.fecha = fecha;
		this.total = total;
		this.estado = estado;
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
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getEstado() {
		return this.estado;
	}
	public void setEstado(String estado) {
		this.estado  = estado;
	}
	
	
}
