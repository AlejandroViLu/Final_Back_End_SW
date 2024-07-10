package com.finalSW.CRUD.entidad;

import org.springframework.data.mongodb.core.mapping.Document;

import com.finalSW.global.entity.EntityId;

@Document(collection = "Categoria")
public class Categoria extends EntityId{
    private String nombre;
    private String descripcion;
	public Categoria(int id, String nombre, String descripcion) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	
	@Override
	public int getId() {
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}