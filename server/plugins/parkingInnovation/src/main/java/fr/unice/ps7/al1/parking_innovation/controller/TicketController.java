package fr.unice.ps7.al1.parking_innovation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.unice.ps7.al1.parking_innovation.model.ParkingTicket;
import fr.unice.ps7.al1.parking_innovation.repository.PaymentRepository;
import fr.unice.ps7.al1.parking_innovation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/innovation")
public class TicketController {
	private ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
	@Autowired
	private TicketService ticketService;
	@Autowired
	private PaymentRepository paymentRepository;

	/**
	 * Modify a ticket
	 *
	 * Warning : cannot modify the payment this ways
	 *
	 * @param id the ticket id
	 * @param body the ticket modification (json)
	 * @return the new ticket
	 */
	@PutMapping("/tickets/{id}")
	public Optional<ParkingTicket> ticketModification(
		@PathVariable("id") Long id,
		@RequestBody String body
	) {
		return ticketService
			.findById(id)
			.map((ticket) -> {
				ObjectReader toUp = mapper.readerForUpdating(ticket);
				try {
					ticket = toUp.readValue(body, ParkingTicket.class);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				return ticketService.save(ticket);
			});
	}

	/**
	 * Delete a ticket
	 *
	 * @param id the ticket id
	 */
	@DeleteMapping("/tickets/{id}")
	public void deleteTicket(
		@PathVariable("id") Long id
	) {
		ticketService.deleteById(id);
	}

	/**
	 * Delete a payment
	 *
	 * @param id the payment id
	 */
	@DeleteMapping("/payments/{id}")
	public void deletePayment(
		@PathVariable("id") Long id
	) {
		paymentRepository.deleteById(id);
	}
}
