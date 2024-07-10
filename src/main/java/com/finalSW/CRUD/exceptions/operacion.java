package com.finalSW.CRUD.exceptions;

public class operacion {
	public static String arreglarMensaje(String message) {
		return message.replaceAll("[\\[\\]]", "");
	}
}
