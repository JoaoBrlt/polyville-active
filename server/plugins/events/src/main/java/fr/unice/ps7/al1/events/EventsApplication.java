package fr.unice.ps7.al1.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Events application.
 *
 * The spring boot application of the events plugin.
 */
@SpringBootApplication
public class EventsApplication {
	/**
	 * Runs the events application.
	 * @param args The program arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(EventsApplication.class, args);
	}
}
