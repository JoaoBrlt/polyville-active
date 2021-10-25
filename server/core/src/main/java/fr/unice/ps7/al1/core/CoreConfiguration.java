package fr.unice.ps7.al1.core;

import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * Core configuration.
 *
 * Configures the core application.
 */
@Configuration
public class CoreConfiguration {
	@Lazy
	@Autowired
	private PluginManager pluginManager;

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
					.allowedMethods("*")
					.allowedOrigins("http://localhost:4200");
			}
		};
	}
}
