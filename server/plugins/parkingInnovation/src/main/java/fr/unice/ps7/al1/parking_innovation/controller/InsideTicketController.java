package fr.unice.ps7.al1.parking_innovation.controller;

import fr.unice.ps7.al1.parking.service.ParkingService;
import fr.unice.ps7.al1.parking_innovation.model.ParkingTicket;
import fr.unice.ps7.al1.parking_innovation.model.Payment;
import fr.unice.ps7.al1.parking_innovation.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/innovation/inside-parking")
public class InsideTicketController {
	@Autowired
	private TicketService ticketService;
	@Lazy
	@Autowired
	private ParkingService parkingService;

	/**
	 * Called by the parking's In-Terminal
	 *
	 * Generate a ticket and the client link to activation (in the ticket)
	 *
	 * @return the ticket
	 */
	@PostMapping("/generate")
	public ParkingTicket generateTicket(){
		return ticketService.save(ParkingTicket.generateInsideTicket());
	}

	/**
	 * Called by the parking's In-Terminal
	 *
	 * Generate a ticket and the client link to activation (in the ticket)
	 *
	 * @return the ticket
	 */
	@PostMapping("{parkingId}/generate")
	public ParkingTicket generateTicketForParking(@PathVariable(value = "parkingId") Long parkingId){
		ParkingTicket ticket = ParkingTicket.generateInsideTicket();
		ticket.setParkingId(parkingId);
		return ticketService.save(ticket);
	}


	/**
	 * 	Called by the client
	 *
	 * 	Set the ticket like took one
	 *
	 * @param id the id of the ticket
	 * @return the ticket
	 */
	@PutMapping("/validate/{ticketId}")
	public Optional<ParkingTicket> activateTicket(@PathVariable(value = "ticketId") Long id){
		return ticketService.findById(id).map((ticket1)-> {
			ticket1.takeIt();
			if (ticket1.getParkingId()!=null) parkingService.decrementFreePlaceByParkingId(ticket1.getParkingId());
			return ticketService.save(ticket1);
		});
	}

	/**
	 * Called by the client or a terminal
	 *
	 * Get the ticket
	 * @return the ticket of this id
	 */
	@GetMapping("/tickets/{id}")
	public Optional<ParkingTicket> getTicket(@PathVariable(value = "id") Long id){
		return ticketService.findById(id);
	}

	/**
	 * Called by the parking's Payment-Terminal
	 *
	 * Register that the ticket is well paid
	 *
	 * @return the ticket
	 */
	@PutMapping("/payment/{id}")
	public Optional<ParkingTicket> payTicket(@PathVariable(value = "id") Long id, @RequestBody(required = false) Payment payment){
		Payment registeredPayment = payment==null ? new Payment():payment;

		return ticketService.findById(id).map((ticket)->{
			ticket.addPayment(registeredPayment);
			ticketService.save(ticket);
			return ticket;
		});
	}

	/**
	 * Called by the parking's Out-Terminal
	 *
	 * Register that the ticket-owner is now out
	 *
	 * @return the ticket
	 */
	@PutMapping("/done/{ticketId}")
	public Optional<ParkingTicket> doneTicket(@PathVariable(value = "ticketId") Long id){
		return ticketService.findById(id).map((ticket)->{
			ticket.doneIt();
			if (ticket.getParkingId()!=null)
				parkingService.incrementFreePlaceByParkingId(ticket.getParkingId());
			ticketService.save(ticket);
			return ticket;
		});
	}

	/**
	 * Get all ticket of a parking
	 *
	 * @return the tickets
	 */
	@GetMapping("/{parkingId}/tickets")
	public List<ParkingTicket> getTickets(@PathVariable(value = "parkingId") Long id){
		return ticketService
			.findAll()
			.stream()
			.filter((t)->id.equals(t.getParkingId()))
			.collect(Collectors.toList());
	}
}
