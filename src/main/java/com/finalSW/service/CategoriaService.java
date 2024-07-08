package com.finalSW.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalSW.dto.CategoriaDto;
import com.finalSW.entidad.Categoria;
import com.finalSW.exceptions.AtributteException;
import com.finalSW.exceptions.ResourceNotFoundException;
import com.finalSW.repository.CategoriaInterface;

@Service
public class CategoriaService {
	@Autowired
	CategoriaInterface rto;
	
	public List<Categoria> listarCategorias(){
		return rto.findAll();
	}
	public Categoria buscarCategoria(int id) throws ResourceNotFoundException {
		return rto.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Encontrado"));
	}
	public Categoria insertarCategoria(CategoriaDto a) throws AtributteException {
		if(rto.existsByNombre(a.getNombre()))
			throw new AtributteException("Nombre ya esta en uso");
		
		int id = autoIncrement();
		Categoria producto = new Categoria(id,
				a.getNombre(),
				a.getDescripcion()
				);
		return rto.save(producto);
	}
	public Categoria actualizarCategoria(int id, CategoriaDto a) throws ResourceNotFoundException, AtributteException {
		
		Categoria categoria = rto.findById(id).orElseThrow(()->new ResourceNotFoundException("No Encontrado"));
		
		if(rto.existsByNombre(a.getNombre()) && rto.findByNombre(a.getNombre()).get().getId() != id)
			throw new AtributteException("Nombre ya esta en uso");
		
		categoria.setNombre(a.getNombre());
		categoria.setDescripcion(a.getDescripcion());
		return rto.save(categoria);
	}
	
	public Categoria eliminarCategoria(int id) throws ResourceNotFoundException {
		Categoria cat =  rto.findById(id).orElseThrow(()->new ResourceNotFoundException("No Encontrado"));
		rto.delete(cat);
		return cat;
	}
	
	
	private int autoIncrement() {
		List<Categoria> lista = rto.findAll();
		return lista.isEmpty()? 1:
			lista.stream().max(java.util.Comparator.comparing(Categoria::getId)).get().getId() + 1;
	}
}