package fr.unice.ps7.al1.core.service;

import fr.unice.ps7.al1.common.model.Publication;
import fr.unice.ps7.al1.common.pipe.PublicationPipe;
import org.pf4j.PluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PublicationService {
	@Autowired
	private PluginManager pluginManager;

	public List<Publication> findAll() {
		List<PublicationPipe> list = pluginManager.getExtensions(PublicationPipe.class);
		return list
			.stream()
			.map(PublicationPipe::getElements)
			.flatMap(Collection::stream)
			.collect(Collectors.toList());
	}
}
