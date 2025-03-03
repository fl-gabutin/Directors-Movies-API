package com.movies.Limepay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class LimepayApplication {

	public static void main(String[] args) {
		SpringApplication.run(LimepayApplication.class, args);
	}


}