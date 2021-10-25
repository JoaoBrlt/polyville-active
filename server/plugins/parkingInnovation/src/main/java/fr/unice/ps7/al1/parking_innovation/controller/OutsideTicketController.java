package fr.unice.ps7.al1.parking_innovation.controller;

import fr.unice.ps7.al1.parking.model.Parking;
import fr.unice.ps7.al1.parking.service.ParkingService;
import fr.unice.ps7.al1.parking_innovation.model.ParkingTicket;
import fr.unice.ps7.al1.parking_innovation.model.Payment;
import fr.unice.ps7.al1.parking_innovation.repository.PaymentRepository;
import fr.unice.ps7.al1.parking_innovation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/innovation/outside-parking")
public class OutsideTicketController {
	@Autowired
	private TicketService ticketService;
	@Autowired
	private PaymentRepository paymentRepository;
	@Lazy
	@Autowired
	private ParkingService parkingService;

	/**
	 * Register a payment for a outside parking
	 *
	 * @param numberPlate the number plate of the client car
	 * @param payment the payment he made for start
	 * @return the ticket
	 */
	@PostMapping("/register/{numberplate}")
	public ParkingTicket registerTicket(
		@PathVariable(value = "numberplate") String numberPlate,
		@RequestBody Payment payment
	){
		return ticketService.save(ParkingTicket.generateOutsideTicket(numberPlate, payment));
	}

	/**
	 * Register a payment for a knew outside parking
	 *
	 * @param parkingId the parking id
	 * @param numberPlate the number plate of the client car
	 * @param payment the payment he made for start
	 * @return the ticket
	 */
	@PostMapping("{parkingId}/register/{numberplate}")
	public ParkingTicket registerTicketLinkedToParking(
		@PathVariable(value = "parkingId") Long parkingId,
		@PathVariable(value = "numberplate") String numberPlate,
		@RequestBody Payment payment
	){
		ParkingTicket ticket = ParkingTicket.generateOutsideTicket(numberPlate, payment);
		ticket.setParking(checkForParking(parkingId));
		return ticketService.save(ticket);
	}

	/**
	 * Add a payment to the ticket
	 *
	 * @param id the ticket id
	 * @param payment the payment to add
	 * @return the new ticket
	 */
	@PutMapping("/additional-payment/{ticketId}")
	public Optional<ParkingTicket> addPaymentTicket(
		@PathVariable("ticketId") Long id,
		@RequestBody Payment payment
	){
		return ticketService.findById(id).map(ticket -> {
			payment.setTicket(ticket);
			paymentRepository.save(payment);
			ticket.addPayment(payment);
			return ticket;
		});
	}

	/**
	 * Get all outside ticket
	 *
	 * @return the tickets
	 */
	@GetMapping("/tickets")
	public List<ParkingTicket> getAllTickets(){
		return ticketService
			.findAll()
			.stream()
			.filter((ticket)-> ticket.getParking() != null
				&& ticket.getParking().getOutsideParking())
			.collect(Collectors.toList());
	}

	/**
	 * Get all ticket liked to the parking
	 *
	 * @param parkingId the outside parking id
	 * @return the tickets
	 */
	@GetMapping("/{parkingId}/tickets")
	public List<ParkingTicket> getTickets(
		@PathVariable(value = "parkingId") Long parkingId
	){
		checkForParking(parkingId);
		return ticketService
			.findAll()
			.stream()
			.filter((ticket)-> parkingId.equals(ticket.getParkingId()))
			.collect(Collectors.toList());
	}

	private Parking checkForParking(Long id){
		Optional<Parking> opt = parkingService.findById(id).map(parking -> {
			if (!parking.getOutsideParking())
				throw new RuntimeException("Parking must be a outside one");
			return parking;
		});
		if(opt.isEmpty()) throw new RuntimeException("Parking must exist");
		return opt.get();
	}
}
