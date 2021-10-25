package fr.unice.ps7.al1.parking_innovation.repository;

import fr.unice.ps7.al1.parking_innovation.model.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<ParkingTicket, Long> {
}
