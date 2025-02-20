package com.finalSW.CRUD.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalSW.CRUD.dto.ProductoDto;
import com.finalSW.CRUD.entidad.Producto;
import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.CRUD.exceptions.ResourceNotFoundException;
import com.finalSW.CRUD.repository.ProductoInterface;
import com.finalSW.global.utils.Operations;

@Service
public class ProductoServicio {
	@Autowired
	ProductoInterface rto;
	
	public List<Producto> listarProductos(){
		return rto.findAll();
	}
	public Producto buscarProducto(int id) throws ResourceNotFoundException {
		return rto.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Encontrado"));
	}
	public Producto insertarProducto(ProductoDto a) throws AtributteException {
		if(rto.existsByNombre(a.getNombre()))
			throw new AtributteException("Nombre ya esta en uso");
		
		int id = Operations.autoIncrement(rto.findAll());
		Producto producto = new Producto(id,
				a.getNombre(),
				a.getDescripcion(),
				a.getPrecio(),
				a.getStock(),
				a.getImagen(),
				a.getCategoriaId()
				);
		return rto.save(producto);
	}
	public Producto actualizarProducto(int id, ProductoDto a) throws ResourceNotFoundException, AtributteException {
		
		Producto producto = rto.findById(id).orElseThrow(()->new ResourceNotFoundException("No Encontrado"));
		
		if(rto.existsByNombre(a.getNombre()) && rto.findByNombre(a.getNombre()).get().getId() != id)
			throw new AtributteException("Nombre ya esta en uso");
		
		producto.setNombre(a.getNombre());
		producto.setDescripcion(a.getDescripcion());
		producto.setPrecio(a.getPrecio());
		producto.setStock(a.getStock());
		producto.setImagen(a.getImagen());
		producto.setCategoriaId(a.getCategoriaId());
		return rto.save(producto);
	}
	
	public Producto eliminarProducto(int id) throws ResourceNotFoundException {
		Producto pro =  rto.findById(id).orElseThrow(()->new ResourceNotFoundException("No Encontrado"));
		rto.delete(pro);
		return pro;
	}
}
