package com.finalSW.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalSW.dto.CategoriaDto;
import com.finalSW.entidad.Categoria;
import com.finalSW.exceptions.AtributteException;
import com.finalSW.exceptions.MessageDto;
import com.finalSW.exceptions.ResourceNotFoundException;
import com.finalSW.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "http://localhost:4200")

public class CategoriaController{
	@Autowired
	CategoriaService us;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias(){
		return ResponseEntity.ok(us.listarCategorias());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarCategoria(@PathVariable("id") int id) throws ResourceNotFoundException{
		return ResponseEntity.ok(us.buscarCategoria(id));
	}
	@PostMapping
	public ResponseEntity<MessageDto> insertarProducto(@Valid @RequestBody CategoriaDto cat) throws AtributteException{
		Categoria categoria = us.insertarCategoria(cat);
		String mensaje = "Producto "+categoria.getNombre()+ " ha sido agregado";
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}
	@PutMapping("/{id}")
	public ResponseEntity<MessageDto> actualizarProducto(@PathVariable("id") int id, @Valid @RequestBody CategoriaDto cat) throws ResourceNotFoundException, AtributteException{
		Categoria categoria = us.actualizarCategoria(id, cat);
		String mensaje = "Producto "+categoria.getNombre()+ " ha sido actualizado";
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDto> eliminarProducto(@PathVariable("id") int id) throws ResourceNotFoundException{
		Categoria categoria = us.eliminarCategoria(id);
		String mensaje = "Producto "+categoria.getNombre()+ " ha sido ELIMINADO";
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}
}