package fr.unice.ps7.al1.events;

import fr.unice.ps7.al1.common.interfaces.CustomPlugin;
import org.laxture.sbp.SpringBootPlugin;
import org.laxture.sbp.spring.boot.SpringBootstrap;
import org.pf4j.PluginWrapper;

/**
 * Events plugin.
 *
 * Implements the event related features.
 */
public class EventsPlugin extends SpringBootPlugin implements CustomPlugin {
	public final static String PLUGIN_ID = "events";

	/**
	 * Constructs the event plugin.
	 * @param wrapper The plugin wrapper.
	 */
	public EventsPlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	/**
	 * Creates the spring bootstrap.
	 * @return The spring bootstrap.
	 */
	@Override
	protected SpringBootstrap createSpringBootstrap() {
		return new SpringBootstrap(this, EventsApplication.class);
	}

	/**
	 * Starts the event plugin.
	 */
	@Override
	public void start() {
		System.out.println("Start the events plugin.");
		super.start();
	}

	/**
	 * Stops the event plugin.
	 */
	@Override
	public void stop() {
		System.out.println("Stop the events plugin.");
		super.stop();
	}

	/**
	 * Returns the name of the plugin.
	 * @return The name of the plugin.
	 */
	@Override
	public String getName() {
		return "Events";
	}
}

