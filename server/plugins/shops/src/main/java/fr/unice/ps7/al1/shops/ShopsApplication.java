package fr.unice.ps7.al1.shops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Shops application.
 *
 * The spring boot application of the shops plugin.
 */
@SpringBootApplication
public class ShopsApplication {
	/**
	 * Runs the shops application.
	 * @param args The program arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ShopsApplication.class, args);
	}
}
