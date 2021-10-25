package fr.unice.ps7.al1.core;


import org.laxture.spring.util.ApplicationContextProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
/**
 * Core application.
 *
 * The spring boot application of the core.
 */
@SpringBootApplication(scanBasePackages = "fr.unice.ps7.al1")
public class CoreApplication {
	/**
	 * Runs the core application.
	 * @param args The program arguments.
	 */
	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder();
		builder
			.sources(CoreApplication.class)
			.build()
			.run();
	}

	@Bean
	public ApplicationContextAware multiApplicationContextProviderRegister() {
		return ApplicationContextProvider::registerApplicationContext;
	}
}
