package com.finalSW.CRUD.dto;

import javax.validation.constraints.NotBlank;

public class CategoriaDto {
	@NotBlank(message = "Producto nombre obligatorio")
	private String nombre;
    @NotBlank(message = "Producto descripcion obligatorio")
    private String descripcion;
    public CategoriaDto(String nombre, String descripcion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
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
