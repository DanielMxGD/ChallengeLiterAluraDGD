package com.alura.LiterAluraChallengeDGD;

import com.alura.LiterAluraChallengeDGD.Convertidores.ConsumoApi;
import com.alura.LiterAluraChallengeDGD.Convertidores.Deserializacion;
import com.alura.LiterAluraChallengeDGD.Model.DatosGenerales;
import com.alura.LiterAluraChallengeDGD.Model.Libro;
import com.alura.LiterAluraChallengeDGD.Repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiterAluraChallengeDgdApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraChallengeDgdApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repository);
		principal.menuOpciones();

	}
}
