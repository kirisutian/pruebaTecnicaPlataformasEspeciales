package com.christian.persistencia.service;

import java.util.List;

import com.christian.persistencia.dto.TransaccionRequest;
import com.christian.persistencia.dto.TransaccionResponse;

public interface TransaccionService {
	
	List<TransaccionResponse> obtenerTodas();
	
	TransaccionResponse buscarPorReferencia(String referencia);
	
	TransaccionResponse guardar(TransaccionRequest request);
	
	void cancelar(Long id, String referencia, String estatus);

}
