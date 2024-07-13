package com.finalSW.CRUD.entidad;

import org.springframework.data.mongodb.core.mapping.Document;

import com.finalSW.global.entity.EntityId;

@Document(collection = "Producto")
public class Producto extends EntityId{
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private String imagen;
    private int categoriaId;
	public Producto(int id, String nombre, String descripcion, double precio, int stock, String imagen,
			int categoriaId) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.imagen = imagen;
		this.categoriaId = categoriaId;
	}
	
	public Producto() {
		// TODO Auto-generated constructor stub
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
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public int getCategoriaId() {
		return categoriaId;
	}
	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

    
}