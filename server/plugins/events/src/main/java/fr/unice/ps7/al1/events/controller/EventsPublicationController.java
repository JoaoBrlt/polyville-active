package fr.unice.ps7.al1.events.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.unice.ps7.al1.events.controller.error.BadInputException;
import fr.unice.ps7.al1.events.model.Event;
import fr.unice.ps7.al1.events.model.EventPublication;
import fr.unice.ps7.al1.events.service.EventPublicationService;
import fr.unice.ps7.al1.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Event controller.
 *
 * Defines the routes of the events plugin.
 */
@RestController
@RequestMapping("/eventsPublications")
public class EventsPublicationController {
	@Autowired
	private EventPublicationService eventPublicationService;
	@Autowired
	private EventService eventService;
	private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@GetMapping("")
	public List<EventPublication> getPublications(){
		return eventPublicationService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<EventPublication> getPublication(@PathVariable(value="id") Long id) {
		return eventPublicationService.findById(id);
	}

	@PostMapping("")
	public EventPublication postPublication(@RequestBody EventPublication publication) {
		if(publication.getId()!=null && eventPublicationService.existsById(publication.getId()))
			throw new BadInputException("A publication  of this id already exist");
		if(publication.getEvent()!= null){
			Optional<Event> event = (publication.getEvent().getId()!=null) ? eventService.findById(publication.getEvent().getId()) : Optional.empty();
			if(event.isEmpty())
				throw new BadInputException("A event of id "+publication.getEvent().getId()+" not exist");

			// to not modify the event
			publication.setOwner(event.get());
		} else throw new BadInputException("The event must be not null");

		return eventPublicationService.save(publication);
	}

	@PutMapping("/{id}")
	public EventPublication modifyPublication(@PathVariable(value="id") Long id, @RequestBody String body) throws IOException {
		Optional<EventPublication> opt = eventPublicationService.findById(id);
		if(opt.isEmpty())
			throw new BadInputException("A publication of this id do not exist");

		// merge
		ObjectReader toUp = mapper.readerForUpdating(opt.get());
		EventPublication publication = toUp.readValue(body, EventPublication.class);

		// check for id modification
		if(!id.equals(publication.getId()))
				throw new BadInputException("No id authorized in body");

		// check for unknown event
		if(publication.getEvent()!= null){
			Optional<Event> event = eventService.findById(publication.getEvent().getId());
			if(event.isEmpty())
				throw new BadInputException("A event of id "+publication.getEvent().getId()+" not exist");

			// to not modify the event
			publication.setOwner(event.get());
		}

		return eventPublicationService.save(publication);
	}

	@DeleteMapping("/{id}")
	public void deletePublication(@PathVariable(value="id") Long id) {
		if(!eventPublicationService.existsById(id))
			throw new BadInputException("A publication of this id do not exist");

		eventPublicationService.deleteById( id);
	}
}
