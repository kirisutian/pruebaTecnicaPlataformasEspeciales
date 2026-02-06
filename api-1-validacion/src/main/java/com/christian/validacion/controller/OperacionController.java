package com.christian.validacion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.christian.validacion.dto.OperacionRequest;
import com.christian.validacion.dto.TransaccionResponse;
import com.christian.validacion.service.OperacionService;
import com.christian.validacion.util.ShaUtil;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/operaciones")
@AllArgsConstructor
public class OperacionController {
	
	private final OperacionService service;
	
	private final ShaUtil shaUtil;
	
	@PostMapping("/debug/sha")
	public ResponseEntity<String> debugSha(@RequestBody String texto) {
	    return ResponseEntity.ok(shaUtil.generarSha256(texto));
	}
	
	@PostMapping
	public ResponseEntity<TransaccionResponse> procesarOperacion(@Valid @RequestBody OperacionRequest request) {
		return ResponseEntity.ok(service.procesarOperacion(request));
	}

}
