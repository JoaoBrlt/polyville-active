package fr.unice.ps7.al1.events.repository;

import fr.unice.ps7.al1.events.model.EventPublication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventPublicationRepository extends JpaRepository<EventPublication, Long> {
}
