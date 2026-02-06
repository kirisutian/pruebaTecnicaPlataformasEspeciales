package com.christian.validacion.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.christian.validacion.dto.TransaccionRequest;
import com.christian.validacion.dto.TransaccionResponse;

@FeignClient(name = "api-2-persistencia", url = "http://localhost:8082")
public interface TransaccionClient {
	
	@PostMapping("/api/transacciones")
	TransaccionResponse crear(@RequestBody TransaccionRequest request);

}
