package com.projeto_pi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class ProjetoPiApplication {

	public static void main(String[] args) {
		Dotenv.configure().load();
		SpringApplication.run(ProjetoPiApplication.class, args);
	}

}
