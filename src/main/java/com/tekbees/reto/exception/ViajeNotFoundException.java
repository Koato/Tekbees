package com.tekbees.reto.exception;

import org.springframework.core.NestedRuntimeException;

public class ViajeNotFoundException extends NestedRuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViajeNotFoundException(String id) {
		super("El viaje con ID '" + id + "' no ha sido encontrado");
	}

}
