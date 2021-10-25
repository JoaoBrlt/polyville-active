package fr.unice.ps7.al1.events.service;

import fr.unice.ps7.al1.events.model.Event;
import fr.unice.ps7.al1.events.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
	@Autowired
	private EventRepository repository;

	public List<Event> findAll() {
		return repository.findAll();
	}

	public Optional<Event> findById(Long id) {
		return repository.findById(id);
	}

	public Event save(Event event) {
		return repository.save(event);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
}
