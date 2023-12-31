package com.personal.doctor.CapstoneDesign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableCaching
@EnableJpaAuditing
@SpringBootApplication
public class CapstoneDesignApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneDesignApplication.class, args);
	}

}
