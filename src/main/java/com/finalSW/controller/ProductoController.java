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

import com.finalSW.dto.ProductoDto;
import com.finalSW.entidad.Producto;
import com.finalSW.exceptions.AtributteException;
import com.finalSW.exceptions.MessageDto;
import com.finalSW.exceptions.ResourceNotFoundException;
import com.finalSW.service.ProductoServicio;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController{
	@Autowired
	ProductoServicio us;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listarProductos(){
		return ResponseEntity.ok(us.listarProductos());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Producto> buscarProducto(@PathVariable("id") int id) throws ResourceNotFoundException{
		return ResponseEntity.ok(us.buscarProducto(id));
	}
	@PostMapping
	public ResponseEntity<MessageDto> insertarProducto(@Valid @RequestBody ProductoDto pro) throws AtributteException{
		Producto producto = us.insertarProducto(pro);
		String mensaje = "Producto "+producto.getNombre()+ " ha sido agregado";
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}
	@PutMapping("/{id}")
	public ResponseEntity<MessageDto> actualizarProducto(@PathVariable("id") int id, @Valid @RequestBody ProductoDto usu) throws ResourceNotFoundException, AtributteException{
		Producto producto = us.actualizarProducto(id, usu);
		String mensaje = "Producto "+producto.getNombre()+ " ha sido actualizado";
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDto> eliminarProducto(@PathVariable("id") int id) throws ResourceNotFoundException{
		Producto producto = us.eliminarProducto(id);
		String mensaje = "Producto "+producto.getNombre()+ " ha sido ELIMINADO";
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}
}
