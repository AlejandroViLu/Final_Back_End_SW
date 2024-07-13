package com.finalSW.CRUD.entidad;

import org.springframework.data.mongodb.core.mapping.Document;

import com.finalSW.global.entity.EntityId;

@Document(collection = "Categoria")
public class Categoria extends EntityId{
    private String nombre;
    private String descripcion;
    private String imagen;
	public Categoria(int id, String nombre, String descripcion, String imagen) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
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

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
}