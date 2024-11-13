package com.jogonca.api_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiBackendApplication {
	
	public static String phash = null;
	
	public static void main(String[] args) {
		SpringApplication.run(ApiBackendApplication.class, args);
	}

}
