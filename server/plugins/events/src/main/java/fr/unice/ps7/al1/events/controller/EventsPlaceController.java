package fr.unice.ps7.al1.events.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.unice.ps7.al1.events.controller.error.BadInputException;
import fr.unice.ps7.al1.events.model.Event;
import fr.unice.ps7.al1.events.model.EventPlace;
import fr.unice.ps7.al1.events.model.EventPublication;
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
@RequestMapping("/eventsPlaces")
public class EventsPlaceController {
	@Autowired
	private EventPlaceService eventPlaceService;
	@Autowired
	private EventService eventService;
	private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@GetMapping("")
	public List<EventPlace> getPlaces() {
		return eventPlaceService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<EventPlace> getPlace(@PathVariable(value="id") Long id) {
		return eventPlaceService.findById(id);
	}

	@GetMapping("/{id}/events")
	public List<Event> getEventsLocatedHere(@PathVariable(value="id") Long id) {
		return eventService.findAll()
			.stream()
			.filter(e -> e.getLocation()!=null && e.getLocation().getId().equals(id))
			.collect(Collectors.toList());
	}

	@PostMapping("")
	public EventPlace postPlace(@RequestBody EventPlace place) {
		if(place.getId()!= null && eventPlaceService.existsById(place.getId()))
			throw new BadInputException("This id of place already exist");
		return eventPlaceService.save(place);
	}

	@PutMapping("/{id}")
	public EventPlace postPlace(@PathVariable(value="id") Long id, @RequestBody String body) throws IOException {
		Optional<EventPlace> opt = eventPlaceService.findById(id);
		if(opt.isEmpty())
			throw new BadInputException("A place of this id do not exist");

		// merge
		ObjectReader toUp = mapper.readerForUpdating(opt.get());
		EventPlace place = toUp.readValue(body, EventPlace.class);

		// check for id modification
		if(!id.equals(place.getId()))
			throw new BadInputException("No id authorized in body");
		return eventPlaceService.save(place);
	}

	@DeleteMapping("/{id}")
	public void deletePlace(@PathVariable(value="id") Long id) {
		if(!eventPlaceService.existsById(id))
			throw new BadInputException("This id of place not exist");
		eventPlaceService.deleteById(id);
	}
}
