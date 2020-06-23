package edu.eci.arsw.covid19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase main de la aplicaion Covid19 Cases Tool
 */
@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.covid19"})
public class Covid19Application {

	/**
	 * Meteodo main de la clase main
	 * @param args Los argumetos que requiere
	 */
	public static void main(String[] args) {
		SpringApplication.run(Covid19Application.class, args);
	}
}
