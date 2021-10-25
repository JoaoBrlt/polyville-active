package fr.unice.ps7.al1.core.controller;

import org.laxture.sbp.SpringBootPluginManager;
import org.pf4j.PluginDescriptor;
import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Plugin controller.
 * <p>
 * Defines the routes to manage the plugins.
 */
@RestController
@RequestMapping("${spring.sbp.controller.base-path:/sbp}")
public class PluginController {
	@Lazy
	@Autowired
	private SpringBootPluginManager pluginManager;

	/**
	 * Returns the unresolved plugins.
	 *
	 * @return The unresolved plugins.
	 */
	private List<Path> getUnresolvedPlugins() {
		// Get all the plugins.
		List<Path> pluginPaths = pluginManager.getPluginRepository().getPluginPaths();

		// Get the resolved plugins.
		List<PluginWrapper> resolvedPlugins = pluginManager.getPlugins();

		// Deduce the unresolved plugins.
		return pluginPaths
			.stream()
			.filter(path -> resolvedPlugins
				.stream()
				.noneMatch(plugin -> plugin.getPluginPath().equals(path))
			)
			.collect(Collectors.toList());
	}

	@PostMapping(value = "/resolve-all")
	public ResponseEntity<String> resolveAll() {
		// Get the unloaded plugins.
		List<Path> unresolvedPlugins = getUnresolvedPlugins();

		// Start all the unloaded plugins.
		unresolvedPlugins.forEach(path -> pluginManager.loadPlugin(path));

		return ResponseEntity.ok().body("The new plugins have been resolved.");
	}

	@PostMapping(value = "/resolve/{pluginId}")
	public ResponseEntity<String> resolve(@PathVariable String pluginId) {
		// Get the unloaded plugins.
		List<Path> unresolvedPlugins = getUnresolvedPlugins();

		// Search the unloaded plugin.
		for (Path path : unresolvedPlugins) {
			PluginDescriptor descriptor = pluginManager
				.getPluginDescriptorFinder()
				.find(path);

			if (descriptor.getPluginId().equals(pluginId)) {
				// Start the unload plugin.
				pluginManager.loadPlugin(path);
				return ResponseEntity.ok().body("The new plugin has been resolved.");
			}
		}

		return ResponseEntity.notFound().build();
	}
}
