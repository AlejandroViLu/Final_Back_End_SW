package com.finalSW.exceptions;

public class operacion {
	public static String arreglarMensaje(String message) {
		return message.replaceAll("[\\[\\]]", "");
	}
}
