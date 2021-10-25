package fr.unice.ps7.al1.core.service;

import fr.unice.ps7.al1.common.model.Establishment;
import fr.unice.ps7.al1.common.pipe.EstablishmentPipe;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstablishmentService {
	@Autowired
	private PluginManager pluginManager;

	public List<Establishment> findAll() {
		return pluginManager
			.getExtensions(EstablishmentPipe.class)
			.stream()
			.map(EstablishmentPipe::getElements)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}
}
