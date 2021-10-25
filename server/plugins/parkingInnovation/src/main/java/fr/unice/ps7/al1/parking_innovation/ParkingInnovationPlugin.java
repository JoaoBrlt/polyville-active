package fr.unice.ps7.al1.parking_innovation;

import org.laxture.sbp.SpringBootPlugin;
import org.laxture.sbp.spring.boot.SpringBootstrap;
import org.pf4j.PluginWrapper;

import fr.unice.ps7.al1.common.interfaces.CustomPlugin;

/**
 * ParkingInnovation plugin.
 *
 * Implements the innovation related features.
 */
public class ParkingInnovationPlugin extends SpringBootPlugin implements CustomPlugin {
	public final static String PLUGIN_ID = "parkingInnovation";

	/**
	 * Constructs the innovation plugin.
	 * @param wrapper The plugin wrapper.
	 */
	public ParkingInnovationPlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	/**
	 * Creates the spring bootstrap.
	 * @return The spring bootstrap.
	 */
	@Override
	protected SpringBootstrap createSpringBootstrap() {
		return new SpringBootstrap(this, ParkingInnovationApplication.class);
	}

	/**
	 * Starts the innovation plugin.
	 */
	@Override
	public void start() {
		System.out.println("Start the innovation plugin.");
		super.start();
		getApplicationContext()
			.getBeanFactory()
			.registerSingleton("parkingService", getMainApplicationContext().getBean("parkingService"));
	}

	/**
	 * Stops the innovation plugin.
	 */
	@Override
	public void stop() {
		System.out.println("Stop the innovation plugin.");
		super.stop();
	}

	/**
	 * Returns the name of the plugin.
	 * @return The name of the plugin.
	 */
	@Override
	public String getName() {
		return "ParkingInnovation";
	}
}
