package com.christian.validacion.service;

import org.springframework.stereotype.Service;

import com.christian.validacion.client.TransaccionClient;
import com.christian.validacion.dto.OperacionRequest;
import com.christian.validacion.dto.TransaccionResponse;
import com.christian.validacion.exception.FirmaInvalidaException;
import com.christian.validacion.mapper.OperacionMapper;
import com.christian.validacion.util.ShaUtil;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class OperacionServiceImpl implements OperacionService {
	
	private final ShaUtil shaUtil;
	
	private final OperacionMapper operacionMapper;
	
	private final TransaccionClient transaccionClient;

	@Override
	public TransaccionResponse procesarOperacion(OperacionRequest request) {
		log.info("Procesando operación...");
		
		String textoPlano = construirTextoPlano(request);
		log.info("Texto plano generado: {}", textoPlano);
		
		String firmaCalculada = shaUtil.generarSha256(textoPlano);
		log.info("Firma generada: {}", firmaCalculada);
		
		if (!firmaCalculada.equalsIgnoreCase(request.firma())) {
			throw new FirmaInvalidaException("La firma no es válida");
		}
		
		return transaccionClient.crear(operacionMapper.toTransaccionRequest(request));
		
	}
	
	private String construirTextoPlano(OperacionRequest request) {
		return request.operacion() + "|" + request.importe() + "|" + request.cliente();
	}

}
