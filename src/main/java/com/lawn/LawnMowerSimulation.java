package com.lawn;

import com.lawn.model.Lawn;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LawnMowerSimulation {

	public static void main(String[] args) {
		SpringApplication.run(LawnMowerSimulation.class, args);
	}


	@Bean
	public static Lawn lawn() {
		return new Lawn();
	}
}
