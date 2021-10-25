package fr.unice.ps7.al1.events.service;

import fr.unice.ps7.al1.common.service.PrivateService;
import fr.unice.ps7.al1.events.model.EventPublication;
import fr.unice.ps7.al1.events.repository.EventPublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventPublicationService implements PrivateService<EventPublication> {
	@Autowired
	private EventPublicationRepository repository;

	@Override
	public <S extends EventPublication> S save(S toRegister) {
		return repository.save(toRegister);
	}

	@Override
	public void delete(EventPublication toDelete) {
		repository.delete(toDelete);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public Optional<EventPublication> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	@Override
	public List<EventPublication> findAll() {
		return repository.findAll();
	}
}
