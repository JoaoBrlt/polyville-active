package fr.unice.ps7.al1.events.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.unice.ps7.al1.events.controller.error.BadInputException;
import fr.unice.ps7.al1.events.model.Event;
import fr.unice.ps7.al1.events.model.EventManager;
import fr.unice.ps7.al1.events.service.EventManagerService;
import fr.unice.ps7.al1.events.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/eventsManagers")
public class EventManagerController {
	@Autowired
	private EventManagerService eventManagerService;
	@Autowired
	private EventService eventService;
	private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@GetMapping("")
	public List<EventManager> getManagers() {
		return eventManagerService.findAll();
	}

	@GetMapping("/{id}")
	public Optional<EventManager> getManager(@PathVariable(value="id") Long id) {
		return eventManagerService.findById(id);
	}

	@GetMapping("/{id}/events")
	public List<Event> getEventsManaged(@PathVariable(value="id") Long id) {
		return eventService.findAll()
			.stream()
			.filter(e -> e.getOwner()!=null && e.getOwner().getId().equals(id))
			.collect(Collectors.toList());
	}

	@PostMapping("")
	public EventManager postManager(@RequestBody EventManager manager) {
		if(manager.getId()!= null && eventManagerService.existsById(manager.getId()))
			throw new BadInputException("This id of manager already exist");
		return eventManagerService.save(manager);
	}

	@PutMapping("/{id}")
	public EventManager postManager(@PathVariable(value="id") Long id, @RequestBody String body) throws IOException {
		Optional<EventManager> opt = eventManagerService.findById(id);
		if(opt.isEmpty())
			throw new BadInputException("A manager of this id do not exist");

		// merge
		ObjectReader toUp = mapper.readerForUpdating(opt.get());
		EventManager manager = toUp.readValue(body, EventManager.class);

		// check for id modification
		if(!id.equals(manager.getId()))
			throw new BadInputException("No id authorized in body");
		return eventManagerService.save(manager);
	}

	@DeleteMapping("/{id}")
	public void deleteManager(@PathVariable(value="id") Long id) {
		if(!eventManagerService.existsById(id))
			throw new BadInputException("This id of manager not exist");
		eventManagerService.deleteById(id);
	}
}
