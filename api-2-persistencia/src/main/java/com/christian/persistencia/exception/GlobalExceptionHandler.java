package com.christian.persistencia.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.christian.persistencia.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(NoSuchElementException ex) {
		log.warn(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(
						ex.getMessage(),
						List.of(),
						LocalDateTime.now()
				));
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleBadRequest(IllegalArgumentException ex) {
		log.error(ex.getMessage());
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
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleConflict(DataIntegrityViolationException ex) {
		log.error(ex.getMessage(), ex.getMostSpecificCause());
		return ResponseEntity.status(HttpStatus.CONFLICT)
				.body(new ErrorResponse(
						"Conflicto de datos",
						List.of("La referencia ya existe o viola una restricción"),
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
