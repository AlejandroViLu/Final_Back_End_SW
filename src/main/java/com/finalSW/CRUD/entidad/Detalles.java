package com.finalSW.CRUD.entidad;

import org.springframework.data.mongodb.core.mapping.Document;

import com.finalSW.global.entity.EntityId;

@Document(collection = "Detalles")
public class Detalles extends EntityId{
	private int IdVenta;
	private Producto producto;
	private int Cantidad;
	private double subtotal;
	private char Estado;
	public Detalles(int id, int idVenta, Producto producto, int cantidad, double subtotal, char estado) {
		this.id = id;
		this.IdVenta = idVenta;
		this.producto = producto;
		this.Cantidad = cantidad;
		this.subtotal = subtotal;
		this.Estado = estado;
	}
	public Detalles() {
		
	}
	
	@Override
	public int getId() {
		return super.getId();
	}


	@Override
	public void setId(int id) {
		super.setId(id);
	}


	public int getIdVenta() {
		return IdVenta;
	}
	public void setIdVenta(int idVenta) {
		this.IdVenta = idVenta;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public int getCantidad() {
		return Cantidad;
	}
	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	public double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	public char getEstado() {
		return Estado;
	}
	public void setEstado(char estado) {
		Estado = estado;
	}
}
