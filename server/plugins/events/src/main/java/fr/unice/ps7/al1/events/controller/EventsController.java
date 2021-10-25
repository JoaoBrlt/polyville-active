package fr.unice.ps7.al1.events.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.unice.ps7.al1.events.controller.error.BadInputException;
import fr.unice.ps7.al1.events.model.Event;
import fr.unice.ps7.al1.events.model.EventManager;
import fr.unice.ps7.al1.events.model.EventPlace;
import fr.unice.ps7.al1.events.model.EventPublication;
import fr.unice.ps7.al1.events.service.EventManagerService;
import fr.unice.ps7.al1.events.service.EventPlaceService;
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
@RequestMapping("/events")
public class EventsController {
	@Autowired
	private EventService eventService;

	private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@GetMapping("")
	public List<Event> getEvents() {
		return eventService.findAll();
	}

	@PostMapping("")
	public Event postEvent(@RequestBody Event event) {
		// check for double
		if(event.getId()!=null && eventService.existsById(event.getId()))
			throw new BadInputException("Event of id "+event.getId().toString()+" already exist");

		event.setOwner(checkForManagerOrCreate(event.getOwner()));
		event.setLocation(checkForPlaceOrCreate(event.getLocation()));
		return eventService.save(event);
	}

	@GetMapping("/{id}")
	public Optional<Event> getEvent(@PathVariable(value="id") int id) {
		return eventService.findById((long) id);
	}

	@DeleteMapping("/{id}")
	public void deleteEvent(@PathVariable(value="id") Long id) {
		if(!eventService.existsById(id))
			throw new BadInputException("Event of id "+id+" not exist");
		eventService.deleteById(id);
	}

	@PutMapping("/{id}")
	public Event modifyEvent(@PathVariable(value="id") Long id, @RequestBody String body) throws IOException {
		Optional<Event> eventOpt = eventService.findById(id);
		if(eventOpt.isEmpty())
			throw new BadInputException("No corresponding event found");

		// merge
		ObjectReader toUp = mapper.readerForUpdating(eventOpt.get());
		Event event = toUp.readValue(body, Event.class);
		if(!id.equals(event.getId()))
			throw new BadInputException("No id authorized in body");

		// no modification to place or manager authorized (only creation)
		event.setOwner(checkForManagerOrCreate(event.getOwner()));
		event.setLocation(checkForPlaceOrCreate(event.getLocation()));

		return eventService.save(event);
	}

	@Autowired
	private EventPublicationService eventPublicationService;

	@GetMapping("/{id}/publications")
	public List<EventPublication> getPublications(@PathVariable(value="id") Long id){
		if(!eventService.existsById(id))
			throw new BadInputException("The id event "+id+" isn't attributed");

		return eventPublicationService.findAll()
			.stream()
			.filter((p)->p.getEvent()!=null && p.getEvent().getId().equals(id))
			.collect(Collectors.toList());
	}

	//////////////////////////////////////////////////////
	////////////  check for sub-object   /////////////////

	@Autowired
	private EventPlaceService eventPlaceService;

	private EventPlace checkForPlaceOrCreate(EventPlace place){
		if(place==null) return null;
		Optional<EventPlace> opt = (place.getId()!=null) ? eventPlaceService.findById(place.getId()) : Optional.empty();
		if(place.getId() == null || opt.isEmpty()){
			return eventPlaceService.save(place);
		}
		return opt.get();
	}

	//////////////////////////////////////////////////////

	@Autowired
	private EventManagerService eventManagerService;

	private EventManager checkForManagerOrCreate(EventManager manager){
		if(manager==null) return null;
		Optional<EventManager> opt = (manager.getId()!=null) ? eventManagerService.findById(manager.getId()) : Optional.empty();;
		if(manager.getId() == null || opt.isEmpty()){
			return eventManagerService.save(manager);
		}
		return opt.get();
	}
}
