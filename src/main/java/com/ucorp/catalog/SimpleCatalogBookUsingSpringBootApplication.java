package com.ucorp.catalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class SimpleCatalogBookUsingSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleCatalogBookUsingSpringBootApplication.class, args);
	}
}
