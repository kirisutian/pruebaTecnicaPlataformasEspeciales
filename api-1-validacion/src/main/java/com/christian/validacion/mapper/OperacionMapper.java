package com.christian.validacion.mapper;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.christian.validacion.dto.OperacionRequest;
import com.christian.validacion.dto.TransaccionRequest;

@Component
public class OperacionMapper {
	
	public TransaccionRequest toTransaccionRequest(OperacionRequest request) {
		if(request == null) return null;
		
		return new TransaccionRequest(
				request.operacion(),
				new BigDecimal(request.importe()),
				request.cliente(),
				request.firma()
		);
		
	}

}
