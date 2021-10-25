package fr.unice.ps7.al1.parking;

import fr.unice.ps7.al1.parking.service.ParkingService;
import org.laxture.sbp.SpringBootPlugin;
import org.laxture.sbp.spring.boot.SpringBootstrap;
import org.pf4j.PluginWrapper;

import fr.unice.ps7.al1.common.interfaces.CustomPlugin;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Parking plugin.
 *
 * Implements the parking related features.
 */
public class ParkingPlugin extends SpringBootPlugin implements CustomPlugin {
	/**
	 * Constructs the parking plugin.
	 * @param wrapper The plugin wrapper.
	 */
	public ParkingPlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	/**
	 * Creates the spring bootstrap.
	 * @return The spring bootstrap.
	 */
	@Override
	protected SpringBootstrap createSpringBootstrap() {
		return new SpringBootstrap(this, ParkingApplication.class)
			.addSharedBeanName("parkingService");
	}

	/**
	 * Starts the parking plugin.
	 */
	@Override
	public void start() {
		// Start the plugin.
		System.out.println("Start the parking plugin.");
		super.start();

		// Register the plugin wrapper as a bean.
		getApplicationContext()
			.getBeanFactory()
			.registerSingleton("pluginWrapper", getWrapper());

		// Register the parking service.
		registerBeanToMainContext("parkingService", getApplicationContext().getBean("parkingService"));
	}

	/**
	 * Stops the parking plugin.
	 */
	@Override
	public void stop() {
		System.out.println("Stop the parking plugin.");
		super.stop();

		// Remove the parking service.
		unregisterBeanFromMainContext("parkingService");
	}

	/**
	 * Returns the name of the plugin.
	 * @return The name of the plugin.
	 */
	@Override
	public String getName() {
		return "Parking";
	}
}
