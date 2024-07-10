package com.finalSW.CRUD.dto;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;

public class ProductoDto {
    @NotBlank(message = "Producto nombre obligatorio")
	private String nombre;
    @NotBlank(message = "Producto descripcion obligatorio")
    private String descripcion;
    @NotNull(message = "Producto precio obligatorio")
    private double precio;
    @NotNull(message = "Producto stock obligatorio")
    private int stock;
    @NotBlank(message = "Producto imagen obligatorio")
    private String imagen;
    @NotNull(message = "Producto categoria obligatorio")
    private int categoriaId;
	public ProductoDto(String nombre, String descripcion, double precio, int stock, String imagen,
			int categoriaId) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.imagen = imagen;
		this.categoriaId = categoriaId;
	}
	public ProductoDto() {
		
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
