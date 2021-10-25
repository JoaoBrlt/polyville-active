package fr.unice.ps7.al1.core.service;

import fr.unice.ps7.al1.common.model.Place;
import fr.unice.ps7.al1.common.pipe.PlacePipe;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceService {
	@Autowired
	private PluginManager pluginManager;

	public List<Place> findAll() {
		return pluginManager
			.getExtensions(PlacePipe.class)
			.stream()
			.map(PlacePipe::getElements)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}
}
