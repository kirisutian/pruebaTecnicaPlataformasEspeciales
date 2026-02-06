package com.christian.validacion.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record OperacionRequest(
		
		@NotBlank(message = "La operación es requerida")
		@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "La operación solo debe contener letras")
		String operacion,
		
		@NotBlank(message = "El importe es requerido")
		@Pattern(regexp = "^\\d+(\\.\\d{2})$", message = "El importe debe tener formato moneda (Ejemplo: 100.00)")
		String importe,
		
		@NotBlank(message = "El cliente es requerido")
		@Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El cliente solo debe contener letras")
		String cliente,
		
		@NotBlank(message = "La firma es requerida")
		String firma
) {}