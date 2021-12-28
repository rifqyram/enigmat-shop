package com.enigma.enigmat_shop;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Enigmat Shop API",
		description = "Enigmat Shop API for documentation",
		version = "1.0"
))
public class EnigmatShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnigmatShopApplication.class, args);
	}

}
