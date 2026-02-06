package com.christian.validacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Api1ValidacionApplication {

	public static void main(String[] args) {
		SpringApplication.run(Api1ValidacionApplication.class, args);
	}

}
