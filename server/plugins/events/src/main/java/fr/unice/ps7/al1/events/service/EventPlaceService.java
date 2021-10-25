package fr.unice.ps7.al1.events.service;

import fr.unice.ps7.al1.events.model.EventPlace;
import fr.unice.ps7.al1.events.repository.EventPlaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventPlaceService {
	@Autowired
	private EventPlaceRepository repository;

	public List<EventPlace> findAll() {
		return repository.findAll();
	}

	public Optional<EventPlace> findById(Long id) {
		return repository.findById(id);
	}

	public EventPlace save(EventPlace event) {
		return repository.save(event);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
}
