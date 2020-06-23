package edu.eci.arsw.airportfinder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Clase main de la aplicaion AirportFinder
 */
@SpringBootApplication
@ComponentScan(basePackages = {"edu.eci.arsw.airportfinder"})
public class AirportFinderApplication {

	/**
	 * Meteodo main de la clase main
	 * @param args Los argumetos que requiere
	 */
	public static void main(String[] args) {
		SpringApplication.run(AirportFinderApplication.class, args);
	}
}
