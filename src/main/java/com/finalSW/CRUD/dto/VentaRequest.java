package com.finalSW.CRUD.dto;

import java.util.List;

import com.finalSW.CRUD.entidad.Detalles;

public class VentaRequest {
    private VentasDto venta;
    private List<Detalles> listaDetalles
;
	public VentaRequest(VentasDto venta, List<Detalles> listaDetalles
) {
		this.venta = venta;
		this.listaDetalles = listaDetalles
;
	}
	public VentasDto getVenta() {
		return venta;
	}
	public void setVenta(VentasDto venta) {
		this.venta = venta;
	}
	public List<Detalles> getDetallesVenta() {
		return listaDetalles
;
	}
	public void setDetallesVenta(List<Detalles> listaDetalles) {
		this.listaDetalles = listaDetalles;
	}

	

    
}