package com.christian.validacion.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class ShaUtil {
	
	public String generarSha256(String texto) {
		return DigestUtils.sha256Hex(texto);
	}

}
