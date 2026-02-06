package com.christian.persistencia.mapper;

import org.springframework.stereotype.Component;

import com.christian.persistencia.dto.TransaccionRequest;
import com.christian.persistencia.dto.TransaccionResponse;
import com.christian.persistencia.entity.Transaccion;
import com.christian.persistencia.enums.EstatusTransaccion;

@Component
public class TransaccionMapper {
	
	public Transaccion toEntity(TransaccionRequest request, String referencia) {
		
		if(request == null) return null;
		
		return Transaccion.builder()
				.operacion(request.operacion())
				.importe(request.importe())
				.cliente(request.cliente())
				.secreto(request.secreto())
				.referencia(referencia)
				.estatus(EstatusTransaccion.APROBADA)
				.build();
	}
	
	public TransaccionResponse toResponse(Transaccion entity) {
		if(entity == null) return null;
		
		return new TransaccionResponse(
				entity.getId(),
				entity.getEstatus().getEstatus(),
				entity.getReferencia(),
				entity.getOperacion()
		);
	}

}
