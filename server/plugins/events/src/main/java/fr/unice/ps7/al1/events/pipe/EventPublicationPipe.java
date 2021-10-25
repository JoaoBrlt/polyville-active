package fr.unice.ps7.al1.events.pipe;

import fr.unice.ps7.al1.common.model.Publication;
import fr.unice.ps7.al1.common.pipe.PublicationPipe;
import fr.unice.ps7.al1.events.service.EventPublicationService;
import fr.unice.ps7.al1.events.service.EventService;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Extension @Component
public class EventPublicationPipe implements PublicationPipe {
	@Autowired
	private EventPublicationService eventPublicationService;
	@Autowired
	private EventService eventService;

	@Override
	public Collection<Publication> getElements() {
		return Stream.concat(
				eventPublicationService.findAll().stream(),
				eventService.findAll().stream()
			)
			.map((p)->(Publication)p)
			.sorted(Comparator.comparing(Publication::getDate))
			.collect(Collectors.toList());
	}
}
