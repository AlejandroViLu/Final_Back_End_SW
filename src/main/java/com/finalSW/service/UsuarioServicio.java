package com.finalSW.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalSW.dto.UsuarioDto;
import com.finalSW.entidad.Usuario;
import com.finalSW.exceptions.AtributteException;
import com.finalSW.exceptions.ResourceNotFoundException;
import com.finalSW.repository.UsuarioInterface;

@Service
public class UsuarioServicio {
	@Autowired
	UsuarioInterface rto;
	
	public List<Usuario> listarUsuarios(){
		return rto.findAll();
	}
	public Usuario buscarUsuario(int id) throws ResourceNotFoundException {
		return rto.findById(id).orElseThrow(()-> new ResourceNotFoundException("No Encontrado"));
	}
	public Usuario insertarUsuario(UsuarioDto a) throws AtributteException {
		if(rto.existsByusername(a.getUsername()))
			throw new AtributteException("Correo ya en uso");
		int id = autoIncrement();
		Usuario usuario = new Usuario(id, a.getUsername(), a.getLastname(), a.getFirstname(), a.getPhone(), a.getPassword(), a.getRol());
		return rto.save(usuario);
	}
	public Usuario actualizarUsuario(int id, UsuarioDto a) throws ResourceNotFoundException, AtributteException {
		
		Usuario usuario = rto.findById(id).orElseThrow(()->new ResourceNotFoundException("No Encontrado"));
		
		if(rto.findByusername(a.getUsername()).get().getId() != id)
			throw new AtributteException("Id no coincide");
		if(rto.existsByusername(a.getUsername()))
			throw new AtributteException("Correo ya en uso");
		
		return rto.save(usuario);
	}
	
	public Usuario eliminarUsuario(int id) throws ResourceNotFoundException {
		Usuario usu =  rto.findById(id).orElseThrow(()->new ResourceNotFoundException("No Encontrado"));
		rto.delete(usu);
		return usu;
	}
	
	private int autoIncrement() {
		List<Usuario> lista = rto.findAll();
		return lista.isEmpty()? 1:
			lista.stream().max(java.util.Comparator.comparing(Usuario::getId)).get().getId() + 1;
	}
}
