package com.christian.validacion.dto;

public record TransaccionResponse(
		Long id,
		String estatus,
		String referencia,
		String operacion
) {

}
