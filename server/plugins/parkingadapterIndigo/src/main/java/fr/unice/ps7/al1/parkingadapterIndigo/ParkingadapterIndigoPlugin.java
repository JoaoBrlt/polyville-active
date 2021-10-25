package fr.unice.ps7.al1.parkingadapterIndigo;

import org.laxture.sbp.SpringBootPlugin;
import org.laxture.sbp.spring.boot.SpringBootstrap;
import org.pf4j.PluginWrapper;

import fr.unice.ps7.al1.common.interfaces.CustomPlugin;

/**
 * parkingadapterIndigo plugin.
 *
 * Implements the parkingadapterIndigo related features.
 */
public class ParkingadapterIndigoPlugin extends SpringBootPlugin implements CustomPlugin {
	/**
	 * Constructs the parkingadapterIndigo plugin.
	 * @param wrapper The plugin wrapper.
	 */
	public ParkingadapterIndigoPlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	/**
	 * Creates the spring bootstrap.
	 * @return The spring bootstrap.
	 */
	@Override
	protected SpringBootstrap createSpringBootstrap() {
		return new SpringBootstrap(this, ParkingadapterIndigoApplication.class);
	}

	/**
	 * Starts the parkingadapterIndigo plugin.
	 */
	@Override
	public void start() {
		System.out.println("Start the parkingadapterIndigo plugin.");
		super.start();
	}

	/**
	 * Stops the parkingadapterIndigo plugin.
	 */
	@Override
	public void stop() {
		System.out.println("Stop the parkingadapterIndigo plugin.");
		super.stop();
	}

	/**
	 * Returns the name of the plugin.
	 * @return The name of the plugin.
	 */
	@Override
	public String getName() {
		return "ParkingadapterIndigo";
	}
}
