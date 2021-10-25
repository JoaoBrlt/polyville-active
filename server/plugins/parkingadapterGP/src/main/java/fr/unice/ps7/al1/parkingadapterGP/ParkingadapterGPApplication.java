package fr.unice.ps7.al1.parkingadapterGP;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ParkingadapterGPApplication {
	/**
	 * Runs the parkingadapterGP application.
	 * @param args The program arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(ParkingadapterGPApplication.class, args);
	}
}
