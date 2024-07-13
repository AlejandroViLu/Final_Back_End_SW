package com.finalSW.Security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finalSW.CRUD.exceptions.AtributteException;
import com.finalSW.Security.entity.ItemCarrito;
import com.finalSW.Security.entity.Tarjeta;
import com.finalSW.Security.entity.UserEntity;
import com.finalSW.Security.repository.UserEntityRepository;
@Service
public class CarritoService {
	
	@Autowired
	UserEntityRepository r;
	
	public List<ItemCarrito> getCarrito(String username) throws AtributteException {
		UserEntity user = r.findByUsernameOrEmail(username, username).orElseThrow();
		if(user.getCarrito().isEmpty()) {
			throw new AtributteException("Carrito Vacio");
		}
        return user.getCarrito();
    }

	public UserEntity addItem(String username, ItemCarrito itemCarrito) {
		UserEntity user = r.findByUsernameOrEmail(username, username).orElseThrow();
		user.getCarrito().add(itemCarrito);
		return r.save(user);
	}
		
	public UserEntity addTarjeta(String username, Tarjeta tarjeta) {
		UserEntity user = r.findByUsernameOrEmail(username, username).orElseThrow();
		user.setTarjeta(tarjeta);
		return r.save(user);
	}
	public Tarjeta getTarjeta(String username) throws AtributteException {
		UserEntity user = r.findByUsernameOrEmail(username, username).orElseThrow();
		if(user.getTarjeta() == null) {
			throw new AtributteException("Carrito Vacio");
		}
        return user.getTarjeta();
	}
	public UserEntity clearCarrito(String username) {
		UserEntity user = r.findByUsernameOrEmail(username, username).orElseThrow();
	    user.getCarrito().clear();
	    return r.save(user);
	}
	public UserEntity actualizarCantidad(String username, Long productoId, int cantidad) {
        UserEntity user = r.findByUsernameOrEmail(username, username).orElseThrow();
        user.getCarrito().stream()
            .filter(item -> item.getProducto().getId() == (productoId))
            .findFirst()
            .ifPresent(item -> item.setCantidad(cantidad));
        return r.save(user);
    }

    public UserEntity eliminarProducto(String username, Long productoId) {
        UserEntity user = r.findByUsernameOrEmail(username, username).orElseThrow();
        user.getCarrito().removeIf(item -> item.getProducto().getId() == (productoId));
        return r.save(user);
    }
}
