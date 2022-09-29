package com.cloudinfinity.pokemonapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class PokemonapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PokemonapiApplication.class, args);
	}

}
