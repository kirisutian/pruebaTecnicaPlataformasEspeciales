package com.christian.persistencia.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.christian.persistencia.dto.TransaccionRequest;
import com.christian.persistencia.dto.TransaccionResponse;
import com.christian.persistencia.service.TransaccionService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/transacciones")
@AllArgsConstructor
public class TransaccionController {
	
	private final TransaccionService service;
	
	@GetMapping
	public ResponseEntity<List<TransaccionResponse>> listar() {
		return ResponseEntity.ok(service.obtenerTodas());
	}
	
	@GetMapping("/referencia/{referencia}")
	public ResponseEntity<TransaccionResponse> buscarPorReferencia(
			@PathVariable String referencia) {
		return ResponseEntity.ok(service.buscarPorReferencia(referencia));
	}
	
	@PostMapping
	public ResponseEntity<TransaccionResponse> crear(
			@Valid @RequestBody TransaccionRequest request) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(service.guardar(request));
	}
	
	@PatchMapping("/cancelar")
	public ResponseEntity<Void> cancelar(
			@RequestParam Long id,
			@RequestParam String referencia, @RequestParam String estatus) {
		service.cancelar(id, referencia, estatus);
		return ResponseEntity.noContent().build();
	}
}
