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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalSW.CRUD.dto.ProductoDto;
import com.finalSW.CRUD.entidad.Producto;
import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.CRUD.exceptions.MessageDto;
import com.finalSW.CRUD.exceptions.ResourceNotFoundException;
import com.finalSW.Security.service.UploadFileService;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoController {
    
    @Autowired
    com.finalSW.CRUD.service.ProductoServicio us;

	@Autowired
	private UploadFileService upload;
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos() {
        return ResponseEntity.ok(us.listarProductos());
    }

	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Producto> buscarProducto(@PathVariable("id") int id) throws ResourceNotFoundException {
        return ResponseEntity.ok(us.buscarProducto(id));
    }

	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageDto> insertarProducto(
            @Valid @RequestParam("producto") String productoJson,
            @RequestParam("img") MultipartFile file) throws IOException, AtributteException {
		
        ObjectMapper objectMapper = new ObjectMapper();
        ProductoDto pro = objectMapper.readValue(productoJson, ProductoDto.class);
        String nombreImagen = upload.saveImage(file);
        pro.setImagen("http://localhost:8091/images/"+nombreImagen);
        
        Producto producto = us.insertarProducto(pro);
        
        String mensaje = "Producto " + producto.getNombre() + " ha sido agregado";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
    }

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<MessageDto> actualizarProducto(
	    @PathVariable("id") int id,
	    @RequestParam("nombre") String nombre,
	    @RequestParam("descripcion") String descripcion,
	    @RequestParam("precio") double precio,
	    @RequestParam("stock") int stock,
	    @RequestParam("categoriaId") int categoriaId,
	    @RequestParam(value = "imagen", required = false) MultipartFile imagen) 
	        throws ResourceNotFoundException, AtributteException, IOException {
	    ProductoDto producto = new ProductoDto();
	    producto.setNombre(nombre);
	    producto.setDescripcion(descripcion);
	    producto.setPrecio(precio);
	    producto.setStock(stock);
	    producto.setCategoriaId(categoriaId);
	    if (imagen != null && !imagen.isEmpty()) {
	        String nombreImagen = upload.saveImage(imagen);
	        producto.setImagen("http://localhost:8091/images/" + nombreImagen);
	    }
	    us.actualizarProducto(id, producto);

	    String mensaje = "Producto " + producto.getNombre() + " ha sido actualizado";
	    return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> eliminarProducto(@PathVariable("id") int id) throws ResourceNotFoundException {
        Producto producto = us.eliminarProducto(id);
        String mensaje = "Producto " + producto.getNombre() + " ha sido ELIMINADO";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
    }
}
