package fr.unice.ps7.al1.parkingadapterGP;

import org.laxture.sbp.SpringBootPlugin;
import org.laxture.sbp.spring.boot.SpringBootstrap;
import org.pf4j.PluginWrapper;

import fr.unice.ps7.al1.common.interfaces.CustomPlugin;

/**
 * ParkingadapterGP plugin.
 *
 * Implements the parkingadapterGP related features.
 */
public class ParkingadapterGPPlugin extends SpringBootPlugin implements CustomPlugin {
	/**
	 * Constructs the parkingadapterGP plugin.
	 * @param wrapper The plugin wrapper.
	 */
	public ParkingadapterGPPlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	/**
	 * Creates the spring bootstrap.
	 * @return The spring bootstrap.
	 */
	@Override
	protected SpringBootstrap createSpringBootstrap() {
		return new SpringBootstrap(this, ParkingadapterGPApplication.class);
	}

	/**
	 * Starts the parkingadapterGP plugin.
	 */
	@Override
	public void start() {
		System.out.println("Start the parkingadapterGP plugin.");
		super.start();
	}

	/**
	 * Stops the parkingadapterGP plugin.
	 */
	@Override
	public void stop() {
		System.out.println("Stop the parkingadapterGP plugin.");
		super.stop();
	}

	/**
	 * Returns the name of the plugin.
	 * @return The name of the plugin.
	 */
	@Override
	public String getName() {
		return "ParkingadapterGP";
	}
}
