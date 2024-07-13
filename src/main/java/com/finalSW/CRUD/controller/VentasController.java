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

import com.finalSW.CRUD.dto.VentaRequest;
import com.finalSW.CRUD.dto.VentasDto;
import com.finalSW.CRUD.entidad.Ventas;
import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.CRUD.exceptions.MessageDto;
import com.finalSW.CRUD.exceptions.ResourceNotFoundException;
import com.finalSW.CRUD.service.ProductoServicio;
import com.finalSW.CRUD.service.VentasService;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "http://localhost:4200")
public class VentasController {
    
    @Autowired
    VentasService v;
	
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<Ventas>> listarVentas() {
        return ResponseEntity.ok(v.listarVentas());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Ventas> buscarVenta(@PathVariable("id") int id) throws ResourceNotFoundException {
        return ResponseEntity.ok(v.buscarVenta(id));
    }
    
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<MessageDto> insertarVenta(@Valid @RequestBody VentaRequest ventaRequest) throws AtributteException, IOException, ResourceNotFoundException {
    	if (ventaRequest.getDetallesVenta() == null) {
            return ResponseEntity.badRequest().body(new MessageDto(HttpStatus.BAD_REQUEST, "La lista de detalles de la venta no puede ser nula"));
        }
        v.insertarVenta(ventaRequest);
        String mensaje = "Venta ha sido agregado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> actualizarVenta(@PathVariable("id") int id, @Valid @RequestBody VentasDto ven) throws ResourceNotFoundException, AtributteException {
    	v.actualizarVenta(id, ven);
        String mensaje = "Venta ha sido actualizado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> eliminarProducto(@PathVariable("id") int id) throws ResourceNotFoundException {
    	v.eliminarVenta(id);
        String mensaje = "Venta ha sido ELIMINADO";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
    }
}