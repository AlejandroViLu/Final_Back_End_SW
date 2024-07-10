package com.finalSW.CRUD.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AtributteException extends Exception {
	
	public AtributteException(String mensaje) {
		super(mensaje);
	}
}
