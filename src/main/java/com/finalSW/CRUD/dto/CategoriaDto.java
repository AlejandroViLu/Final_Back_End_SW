package com.finalSW.CRUD.dto;

import javax.validation.constraints.NotBlank;

public class CategoriaDto {
	@NotBlank(message = "Categoria nombre obligatorio")
	private String nombre;
    @NotBlank(message = "Categoria descripcion obligatorio")
    private String descripcion;
    @NotBlank(message = "Categoria imagen obligatorio")
    private String imagen;
    public CategoriaDto(String nombre, String descripcion, String imagen) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.imagen = imagen;
	}
    public CategoriaDto() {
		
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
