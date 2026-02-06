package com.christian.persistencia.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
		String mensaje,
		List<String> detalles,
		LocalDateTime timestamp
) {}