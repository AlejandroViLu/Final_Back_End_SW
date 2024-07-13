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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalSW.CRUD.dto.CategoriaDto;
import com.finalSW.CRUD.dto.ProductoDto;
import com.finalSW.CRUD.entidad.Categoria;
import com.finalSW.CRUD.entidad.Producto;
import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.CRUD.exceptions.MessageDto;
import com.finalSW.CRUD.exceptions.ResourceNotFoundException;
import com.finalSW.CRUD.service.CategoriaService;
import com.finalSW.Security.service.UploadFileService;


@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "http://localhost:4200")

public class CategoriaController{
	@Autowired
	CategoriaService us;
	
	@Autowired
	UploadFileService upload;
	
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias(){
		return ResponseEntity.ok(us.listarCategorias());
	}
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> buscarCategoria(@PathVariable("id") int id) throws ResourceNotFoundException{
		return ResponseEntity.ok(us.buscarCategoria(id));
	}
	@PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<MessageDto> insertarCategoria(
            @Valid @RequestParam("categoria") String categoriaJson,
            @RequestParam("img") MultipartFile file) throws IOException, AtributteException {
		
        ObjectMapper objectMapper = new ObjectMapper();
        CategoriaDto pro = objectMapper.readValue(categoriaJson, CategoriaDto.class);
        String nombreImagen = upload.saveImage(file);
        pro.setImagen("http://localhost:8091/images/"+nombreImagen);
        
        Categoria producto = us.insertarCategoria(pro);
        
        String mensaje = "Categoria " + producto.getNombre() + " agregada";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
    }
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<MessageDto> actualizarCategoria(
	    @PathVariable("id") int id,
	    @RequestParam("nombre") String nombre,
	    @RequestParam("descripcion") String descripcion,
	    @RequestParam(value = "imagen", required = false) MultipartFile imagen) 
	        throws ResourceNotFoundException, AtributteException, IOException {
	    CategoriaDto cat = new CategoriaDto();
	    cat.setNombre(nombre);
	    cat.setDescripcion(descripcion);
	    if (imagen != null && !imagen.isEmpty()) {
	        String nombreImagen = upload.saveImage(imagen);
	        System.out.println("Aqui llegue");
	        cat.setImagen("http://localhost:8091/images/" + nombreImagen);
	        System.out.println("Aqui no llegue");
	    }
	    us.actualizarCategoria(id, cat);
	    String mensaje = "Categoria " + cat.getNombre() + " ha sido actualizado";
	    return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDto> eliminarCategoria(@PathVariable("id") int id) throws ResourceNotFoundException{
		Categoria categoria = us.eliminarCategoria(id);
		String mensaje = "Categoria "+categoria.getNombre()+ " ha sido ELIMINADO";
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}
}