package fr.unice.ps7.al1.parking_innovation.service;

import fr.unice.ps7.al1.common.service.PrivateService;
import fr.unice.ps7.al1.parking.model.Parking;
import fr.unice.ps7.al1.parking.repository.ParkingRepository;
import fr.unice.ps7.al1.parking.service.ParkingService;
import fr.unice.ps7.al1.parking_innovation.model.ParkingTicket;
import fr.unice.ps7.al1.parking_innovation.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService implements PrivateService<ParkingTicket> {
	@Autowired
	private TicketRepository repository;
	@Lazy
	@Autowired
	private ParkingService parkingService;

	@Override
	public List<ParkingTicket> findAll() {
		return repository
			.findAll()
			.stream()
			.peek((ticket)->{
				if (ticket.getParkingId()!=null){
					Optional<Parking> parking = parkingService.findById(ticket.getParkingId());
					parking.ifPresent(ticket::setParking);
				}
			})
			.collect(Collectors.toList());
	}

	@Override
	public Optional<ParkingTicket> findById(Long id) {
		return repository
			.findById(id)
			.map((ticket)->{
				if (ticket.getParkingId()!=null) {
					Optional<Parking> parking = parkingService.findById(ticket.getParkingId());
					parking.ifPresent(ticket::setParking);
				}
				return ticket;
			});
	}

	@Override
	public <S extends ParkingTicket> S save(S event) {
		return repository.save(event);
	}

	@Override
	public void delete(ParkingTicket toDelete) {
		deleteById(toDelete.getId());
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	@Override
	public boolean existsById(Long id) {
		return repository.existsById(id);
	}
}
