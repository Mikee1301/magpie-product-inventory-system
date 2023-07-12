package com.magpie.productinventorysystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.magpie.productinventorysystem.repository")
public class ProductInventorySystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(ProductInventorySystemApplication.class, args);
	}
	
}
