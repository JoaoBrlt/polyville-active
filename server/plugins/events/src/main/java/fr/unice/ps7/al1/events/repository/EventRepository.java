package fr.unice.ps7.al1.events.repository;

import fr.unice.ps7.al1.events.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
}
