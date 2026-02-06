package com.christian.validacion.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TransaccionRequest(
		
		@NotBlank(message = "La operación es requerida")
		String operacion,
		
		@NotNull(message = "El importe es requerido")
		BigDecimal importe,
		
		@NotBlank(message = "El cliente es requerido")
		String cliente,
		
		@NotBlank(message = "El secreto es requerido")
		String secreto
) {}