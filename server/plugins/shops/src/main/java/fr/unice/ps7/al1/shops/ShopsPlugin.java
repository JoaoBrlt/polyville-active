package fr.unice.ps7.al1.shops;

import fr.unice.ps7.al1.common.interfaces.CustomPlugin;
import org.laxture.sbp.SpringBootPlugin;
import org.laxture.sbp.spring.boot.SpringBootstrap;
import org.pf4j.PluginWrapper;

/**
 * Shops plugin.
 *
 * Implements the shop related features.
 */
public class ShopsPlugin extends SpringBootPlugin implements CustomPlugin {
	/**
	 * Constructs the shops plugin.
	 * @param wrapper The plugin wrapper.
	 */
	public ShopsPlugin(PluginWrapper wrapper) {
		super(wrapper);
	}

	/**
	 * Creates the spring bootstrap.
	 * @return The spring bootstrap.
	 */
	@Override
	protected SpringBootstrap createSpringBootstrap() {
		return new SpringBootstrap(this, ShopsApplication.class)
			.addSharedBeanName("shopService")
			.addSharedBeanName("discountService");
	}

	/**
	 * Starts the shops plugin.
	 */
	@Override
	public void start() {
		System.out.println("Start the shops plugin.");
		super.start();
	}

	/**
	 * Stops the shops plugin.
	 */
	@Override
	public void stop() {
		System.out.println("Stop the shops plugin.");
		super.stop();
	}

	/**
	 * Returns the name of the plugin.
	 * @return The name of the plugin.
	 */
	@Override
	public String getName() {
		return "Shops";
	}
}
