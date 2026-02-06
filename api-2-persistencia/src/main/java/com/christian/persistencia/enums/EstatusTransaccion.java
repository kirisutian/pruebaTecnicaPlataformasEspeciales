package com.christian.persistencia.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EstatusTransaccion {
	
	APROBADA("Aprobada"),
	CANCELADA("Cancelada");
	
	private final String estatus;
	
	public boolean puedeCancelar() {
        return this == APROBADA;
    }

}
