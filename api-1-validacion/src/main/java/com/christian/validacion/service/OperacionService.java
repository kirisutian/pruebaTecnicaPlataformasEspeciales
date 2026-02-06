package com.christian.validacion.service;

import com.christian.validacion.dto.OperacionRequest;
import com.christian.validacion.dto.TransaccionResponse;

public interface OperacionService {
	
	TransaccionResponse procesarOperacion(OperacionRequest request);

}
