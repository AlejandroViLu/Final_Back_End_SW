package com.finalSW.Security.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finalSW.CRUD.exceptions.MessageDto;
import com.finalSW.Security.entity.Tarjeta;
import com.finalSW.Security.entity.UserEntity;
import com.finalSW.Security.repository.UserEntityRepository;
@RestController
@RequestMapping("/tarjeta")
@CrossOrigin
public class TarjetaController {

    @Autowired
    UserEntityRepository userRepository;
    
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
	@GetMapping("/{username}")
	public ResponseEntity<Tarjeta> obtenerTarjetaUsuario(@PathVariable String username) {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    String currentUsername = authentication.getName();
	    if (!currentUsername.equals(username)) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	    }
	    Optional<UserEntity> user = userRepository.findByUsernameOrEmail(username, username);
	    if (user == null || user.get().getTarjeta()== null) {
	        return ResponseEntity.notFound().build();
	    }
	    Tarjeta tarjeta = user.get().getTarjeta();
	    return ResponseEntity.ok(tarjeta);
	}
	@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @PostMapping
    public ResponseEntity<MessageDto> guardarTarjetaUsuario(@Valid @RequestBody Tarjeta tarjeta) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        Optional<UserEntity> user = userRepository.findByUsernameOrEmail(currentUsername, currentUsername);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageDto(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        }
        user.get().setTarjeta(tarjeta);
        userRepository.save(user.get());
        String mensaje = "Tarjeta guardada correctamente";
        return ResponseEntity.ok(new MessageDto(HttpStatus.OK, mensaje));
    }
}
