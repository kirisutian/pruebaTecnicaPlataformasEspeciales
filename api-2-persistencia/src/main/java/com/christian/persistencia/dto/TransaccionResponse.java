package com.christian.persistencia.dto;

public record TransaccionResponse(
		Long id,
		String estatus,
		String referencia,
		String operacion
) {

}
