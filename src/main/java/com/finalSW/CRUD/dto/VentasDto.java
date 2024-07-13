package com.finalSW.CRUD.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class VentasDto {
    @NotBlank(message = "VentasDto Usuario obligatorio")
	private String username;
    @NotBlank(message = "VentasDto Fecha obligatorio")
	private String fecha;
    @NotNull(message = "VentasDto Total obligatorio")
	private double total;
    @NotBlank(message = "VentasDto Estado obligatorio")
	private String estado;
	public VentasDto(@NotBlank(message = "VentasDto Usuario obligatorio") String username,
			@NotBlank(message = "VentasDto Fecha obligatorio") String fecha,
			@NotNull(message = "VentasDto Total obligatorio") double total,
			@NotBlank(message = "VentasDto Estado obligatorio") String estado) {
		this.username = username;
		this.fecha = fecha;
		this.total = total;
		this.estado = estado;
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
		this.estado = estado;
	}
   
}
