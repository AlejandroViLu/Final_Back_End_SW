package com.finalSW.CRUD.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalSW.CRUD.dto.VentaRequest;
import com.finalSW.CRUD.dto.VentasDto;
import com.finalSW.CRUD.entidad.Detalles;
import com.finalSW.CRUD.entidad.Ventas;
import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.CRUD.exceptions.ResourceNotFoundException;
import com.finalSW.CRUD.repository.DetallesInterface;
import com.finalSW.CRUD.repository.ProductoInterface;
import com.finalSW.CRUD.repository.VentasInterface;
import com.finalSW.Security.repository.UserEntityRepository;
import com.finalSW.global.utils.Operations;
@Service
public class VentasService {
	@Autowired
	VentasInterface rto;
	@Autowired
	UserEntityRepository r;
	@Autowired
	ProductoInterface pi;
	@Autowired
	DetallesInterface di;
	public List<Ventas> listarVentas(){
		return rto.findAll();
	}
	public Ventas buscarVenta(int id) throws ResourceNotFoundException {
		return rto.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Encontrado"));
	}
	public void insertarVenta(VentaRequest ventaRequest) throws AtributteException, ResourceNotFoundException {
	    if(r.existsByUsername(ventaRequest.getVenta().getUsername())) {
	    	Ventas venta = new Ventas(
		            Operations.autoIncrement(rto.findAll()),
		            ventaRequest.getVenta().getUsername(),
		            ventaRequest.getVenta().getFecha(),
		            ventaRequest.getVenta().getTotal(),
		            ventaRequest.getVenta().getEstado()
		    );
		    rto.save(venta);
		    List<Detalles> listaDetalles = ventaRequest.getDetallesVenta();
		    if (listaDetalles != null) {
		        for (Detalles detalle : listaDetalles) {
		        	detalle.setId(Operations.autoIncrement(di.findAll()));
		            detalle.setIdVenta(venta.getId());
		            detalle.setSubtotal(detalle.getCantidad() * detalle.getProducto().getPrecio());
		            di.save(detalle);
		        }
		    } else {
		        throw new AtributteException("La lista2 de detalles de la venta no puede ser nula");
		    }
	    }else {
	        throw new ResourceNotFoundException("Username no encontrado");
	    }
	}

	public Ventas actualizarVenta(int id, VentasDto a) throws ResourceNotFoundException, AtributteException {
		
		Ventas ven = rto.findById(id).orElseThrow(()->new ResourceNotFoundException("No Encontrado"));
		if(r.existsByUsername(a.getUsername())) {
			ven.setUsername(a.getUsername());
			ven.setFecha(a.getFecha());
			ven.setTotal(a.getTotal());
			ven.setEstado(a.getEstado());
			return rto.save(ven);
		}else {
			throw new AtributteException("Username No encontrado");
		}
		
	}
	
	public Ventas eliminarVenta(int id) throws ResourceNotFoundException {
		Ventas pro =  rto.findById(id).orElseThrow(()->new ResourceNotFoundException("No Encontrado"));
		rto.delete(pro);
		return pro;
	}
}
