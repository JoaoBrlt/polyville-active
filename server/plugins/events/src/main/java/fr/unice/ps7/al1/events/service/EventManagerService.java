package fr.unice.ps7.al1.events.service;

import fr.unice.ps7.al1.events.model.EventManager;
import fr.unice.ps7.al1.events.repository.EventManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EventManagerService {
	@Autowired
	private EventManagerRepository repository;

	public <S extends EventManager> S save(S toRegister) {
		return repository.save(toRegister);
	}

	public void delete(EventManager toDelete) {
		repository.delete(toDelete);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public Optional<EventManager> findById(Long id) {
		return repository.findById(id);
	}

	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	public List<EventManager> findAll() {
		return StreamSupport
			.stream(repository.findAll().spliterator(), false)
			.collect(Collectors.toList());
	}
}
