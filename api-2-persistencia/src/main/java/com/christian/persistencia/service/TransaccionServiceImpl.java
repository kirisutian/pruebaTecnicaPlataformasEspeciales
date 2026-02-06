package com.christian.persistencia.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.christian.persistencia.dto.TransaccionRequest;
import com.christian.persistencia.dto.TransaccionResponse;
import com.christian.persistencia.entity.Transaccion;
import com.christian.persistencia.enums.EstatusTransaccion;
import com.christian.persistencia.mapper.TransaccionMapper;
import com.christian.persistencia.repository.TransaccionRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class TransaccionServiceImpl implements TransaccionService {
	
	private final TransaccionRepository transaccionRepository;
	
	private final TransaccionMapper transaccionMapper;
	
	private final SecureRandom secureRandom;

	@Override
	@Transactional(readOnly = true)
	public List<TransaccionResponse> obtenerTodas() {
		log.info("Listado de todas las transacciones solicitado");
		return transaccionRepository.findAll().stream()
				.map(transaccionMapper::toResponse).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public TransaccionResponse buscarPorReferencia(String referencia) {
		log.info("Buscando transacción con referencia: {}", referencia);
		return transaccionRepository.findByReferencia(referencia)
				.map(transaccionMapper::toResponse)
				.orElseThrow(() -> new NoSuchElementException(
						"Transacción no encontrada con referencia: " + referencia));
	}

	@Override
	public TransaccionResponse guardar(TransaccionRequest request) {
		log.info("Registrando transacción para el cliente: ", request.cliente());
		
		String referencia = generarReferencia();
		log.info("Referencia generada: {}", referencia);
		
		Transaccion transaccion = transaccionRepository.save(transaccionMapper.toEntity(request, referencia));
		log.info("Transacción registrada: {}", transaccion);
		
		return transaccionMapper.toResponse(transaccion);
	}

	@Override
	public void cancelar(Long id, String referencia, String estatus) {
		log.info("Cancelando transacción con referencia: {}", referencia);
		
		if (!"cancelar".equalsIgnoreCase(estatus)) {
			throw new IllegalArgumentException("Estatus inválido, solo se permite 'cancelar'");
	    }
		
		Transaccion transaccion = transaccionRepository
				 .findByIdAndReferencia(id, referencia).orElseThrow(() -> 
				 	new NoSuchElementException("Transacción no encontrada con referencia: " + referencia));
		
		if (!transaccion.getEstatus().puedeCancelar()) {
			throw new IllegalArgumentException("No se puede cancelar en estado " + transaccion.getEstatus());
		}
		
		transaccionRepository.actualizarEstatus(id, referencia, EstatusTransaccion.CANCELADA);
	}
	
	private String generarReferencia() {
		return String.valueOf(100000 + secureRandom.nextInt(900000));
	}

}
