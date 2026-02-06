package com.christian.validacion.exception;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.christian.validacion.dto.ErrorResponse;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(FirmaInvalidaException.class)
	public ResponseEntity<ErrorResponse> handleFirmaInvalida(FirmaInvalidaException ex) {
		log.warn(ex.getMessage());
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						ex.getMessage(),
						List.of(),
						LocalDateTime.now()
				));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		List<String> errores = ex.getBindingResult().getFieldErrors()
				.stream()
				.map(e -> e.getField() + ": " + e.getDefaultMessage())
				.toList();
		
		log.warn("Errores en la validación de los datos: {}", errores);
		return ResponseEntity.badRequest()
				.body(new ErrorResponse(
						"Errores en la validación de los datos",
						errores,
						LocalDateTime.now()
				));
	}
	
	@ExceptionHandler(FeignException.class)
	public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex) {
		log.error("Error al llamar API de transacciones. Estatus: {}", ex.status(), ex);
		
		HttpStatus estatus = HttpStatus.resolve(ex.status());
		
		if (estatus == null) {
			estatus = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		
		return ResponseEntity.status(estatus)
				.body(new ErrorResponse(
						"Error al procesar la transacción",
						List.of("Servicio de persistencia no disponible o error de negocio"),
						LocalDateTime.now()
				));
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneral(Exception ex) {
		log.error("Error inesperado", ex);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(
						"Error interno del servidor",
						List.of(ex.getMessage()),
						LocalDateTime.now()
				));
	}

}
