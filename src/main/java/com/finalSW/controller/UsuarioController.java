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

import com.finalSW.dto.UsuarioDto;
import com.finalSW.entidad.Usuario;
import com.finalSW.exceptions.AtributteException;
import com.finalSW.exceptions.MessageDto;
import com.finalSW.exceptions.ResourceNotFoundException;
import com.finalSW.service.UsuarioServicio;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController{
	@Autowired
	UsuarioServicio us;
	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios(){
		return ResponseEntity.ok(us.listarUsuarios());
	}
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> buscarUsuario(@PathVariable("id") int id) throws ResourceNotFoundException{
		return ResponseEntity.ok(us.buscarUsuario(id));
	}
	@PostMapping
	public ResponseEntity<MessageDto> insertarUsuario(@Valid @RequestBody UsuarioDto usu) throws AtributteException{
		Usuario usuario = us.insertarUsuario(usu);
		String mensaje = "Usuario "+usuario.getFirstname()+ " ha sido agregado";
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}
	@PutMapping("/{id}")
	public ResponseEntity<MessageDto> actualizarUsuario(@PathVariable("id") int id, @Valid @RequestBody UsuarioDto usu) throws ResourceNotFoundException, AtributteException{
		Usuario usuario = us.actualizarUsuario(id, usu);
		String mensaje = "Usuario "+usuario.getFirstname()+ " ha sido actualizado";
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<MessageDto> eliminarUsuario(@PathVariable("id") int id) throws ResourceNotFoundException{
		Usuario usuario = us.eliminarUsuario(id);
		String mensaje = "Usuario "+usuario.getFirstname()+ " ha sido ELIMINADO";
		return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
	}
}