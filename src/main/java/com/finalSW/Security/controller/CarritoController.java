package com.finalSW.Security.controller;

import java.util.List;
import java.util.Map;

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

import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.CRUD.exceptions.MessageDto;
import com.finalSW.Security.entity.ItemCarrito;
import com.finalSW.Security.service.CarritoService;

@RestController
@RequestMapping("/carrito")
@CrossOrigin
public class CarritoController {

    @Autowired
    private CarritoService carritoService;
	
	@GetMapping("/{username}")
    public ResponseEntity<List<ItemCarrito>> getCarrito(@PathVariable String username) throws AtributteException {
        return ResponseEntity.ok(carritoService.getCarrito(username));
    }

    @PostMapping("/{username}")
    public ResponseEntity<MessageDto> addItem(@PathVariable String username, @RequestBody ItemCarrito itemCarrito) {
    	carritoService.addItem(username, itemCarrito);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Producto agregado al carrito de "+username));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<MessageDto> clearCarrito(@PathVariable String username) {
    	carritoService.clearCarrito(username);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Carrito de "+username+" limpio."));
    }
    @PutMapping("/{username}/item/{productoId}")
    public ResponseEntity<MessageDto> actualizarCantidad(@PathVariable String username, @PathVariable Long productoId, @RequestBody Map<String, Integer> request) {
        int cantidad = request.get("cantidad");
        carritoService.actualizarCantidad(username, productoId, cantidad);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Cantidad actualizada para el producto en el carrito de " + username));
    }

    @DeleteMapping("/{username}/item/{productoId}")
    public ResponseEntity<MessageDto> eliminarProducto(@PathVariable String username, @PathVariable Long productoId) {
        carritoService.eliminarProducto(username, productoId);
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Producto " + productoId + " eliminado del carrito de " + username));
    }
}
