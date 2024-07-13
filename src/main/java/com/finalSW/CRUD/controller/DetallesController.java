package com.finalSW.CRUD.controller;

import java.io.IOException;
import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalSW.CRUD.dto.ProductoDto;
import com.finalSW.CRUD.entidad.Detalles;
import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.CRUD.exceptions.MessageDto;
import com.finalSW.CRUD.exceptions.ResourceNotFoundException;
import com.finalSW.CRUD.service.DetallesService;
import com.finalSW.CRUD.service.ProductoServicio;

@RestController
@RequestMapping("/detalles")
@CrossOrigin(origins = "http://localhost:4200")
public class DetallesController {
    
    @Autowired
    DetallesService v;
    @Autowired
    ProductoServicio p;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Detalles>> listarDetalles() {
        return ResponseEntity.ok(v.listarDetalles());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Detalles> buscarDetalle(@PathVariable("id") int id) throws ResourceNotFoundException {
        return ResponseEntity.ok(v.buscarDetalle(id));
    }
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<MessageDto> insertarDetalle(@Valid @RequestBody Detalles ven) throws AtributteException, IOException, ResourceNotFoundException {
		ProductoDto pro = new ProductoDto(
				ven.getProducto().getNombre(),
				ven.getProducto().getDescripcion(),
				ven.getProducto().getPrecio(),
				ven.getProducto().getStock()-1,
				ven.getProducto().getImagen(),
				ven.getProducto().getCategoriaId());
		p.actualizarProducto(ven.getProducto().getId(), pro);
		v.insertarDetalle(ven);
        String mensaje = "Detalle agregado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> actualizarDetalle(@PathVariable("id") int id, @Valid @RequestBody Detalles ven) throws ResourceNotFoundException, AtributteException {
    	v.actualizarDetalle(id, ven);
        String mensaje = "Detalle actualizado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> eliminarDetalle(@PathVariable("id") int id) throws ResourceNotFoundException {
    	v.eliminarDetalle(id);
        String mensaje = "Detalle ELIMINADO";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
    }
}