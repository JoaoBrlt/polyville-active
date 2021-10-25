package fr.unice.ps7.al1.parking_innovation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import fr.unice.ps7.al1.common.model.Data;
import fr.unice.ps7.al1.parking.model.Parking;
import fr.unice.ps7.al1.parking_innovation.ParkingInnovationPlugin;
import fr.unice.ps7.al1.parking_innovation.ResourceAccess;
import org.pf4j.Extension;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;


@Entity @Table(name = ParkingTicket.DATA_TABLE)
@Extension
public class ParkingTicket implements Data {
	public final static String DATA_TABLE = "ParkingTicket";
	public final static String TICKET_KIND = "ticket";

	/**
	 * The event identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ticket_sequence")
	@SequenceGenerator(name = "ticket_sequence", sequenceName = "ticket_sequence", allocationSize=1)
	private Long id;

	private ParkingTicket.TicketState state = TicketState.FREE;

	private Long parkingId;

	@Transient
	private Parking parking;

	private LocalDateTime creationDate = LocalDateTime.now();


	@OneToMany(mappedBy = "ticket",
		cascade = CascadeType.ALL,
		fetch = FetchType.EAGER,
		orphanRemoval = true)
	private List<Payment> payments = new LinkedList<>();

	private String carNumberPlate;

	private String placeInfo;

	public ParkingTicket.TicketState getState() {
		return state;
	}

	public LocalDateTime getDate() {
		return creationDate;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getKind() {
		return TICKET_KIND;
	}

	@Override
	public String getPluginId() {
		return ParkingInnovationPlugin.PLUGIN_ID;
	}

	public Long getParkingId() {
		return parkingId;
	}

	public Parking getParking() {
		return parking;
	}

	public String getLinkToClient(){
		return ResourceAccess.generateLinkToClient(this);
	}

	public String getCarNumberPlate() {
		return carNumberPlate;
	}

	public String getPlaceInfo() {
		return placeInfo;
	}

	public Payment getPayment() {
		if (payments.isEmpty()) return null;
		if (payments.size()==1) return payments.toArray(new Payment[1])[0];
		return new ConcatPayment(payments, id);
	}

	public void takeIt() {
		state=TicketState.TOOK;
	}

	public void doneIt() {
		state=TicketState.DONE;
	}

	public void setParking(Parking parking) {
		this.parking = parking;
		this.setParkingId(parking==null? null: parking.getId());
	}

	public void setParkingId(Long parkingId) {
		this.parkingId = parkingId;
	}

	public void addPayment(Payment payment) {
		this.payments.add(payment);
		if (this.state!=TicketState.OUTSIDE) state=TicketState.PAID;
		payment.setTicket(this);
	}

	enum TicketState{
		FREE,
		TOOK,
		PAID,
		DONE,
		OUTSIDE
	}

	private static class ConcatPayment extends Payment{
		private Collection<Payment> payments;
		private Long ticketId;

		ConcatPayment(Collection<Payment> payments, Long ticketId){
			super();
			this.payments = payments;
			this.ticketId = ticketId;
		}

		@Override
		public LocalDateTime getDate() {
			return payments
				.stream()
				.map(Payment::getDate)
				.filter(Objects::nonNull)
				.max(LocalDateTime::compareTo)
				.orElseGet(LocalDateTime::now);
		}

		@Override
		public LocalDateTime getValidityLimit() {
			return payments
				.stream()
				.map(Payment::getValidityLimit)
				.filter(Objects::nonNull)
				.max(LocalDateTime::compareTo)
				.orElseGet(LocalDateTime::now);
		}

		@Override
		public Long getTicketId() {
			return ticketId;
		}

		@Override
		public Double getValue() {
			return payments
				.stream()
				.map(Payment::getValue)
				.filter(Objects::nonNull)
				.mapToDouble(Double::doubleValue)
				.sum();
		}

		//TODO : si on modifie Payment, il faut modifier
	}

	public static ParkingTicket generateInsideTicket(){
		return new ParkingTicket();
	}

	public static ParkingTicket generateOutsideTicket(String numberPlate, Payment payment){
		ParkingTicket result = new ParkingTicket();
		result.state = TicketState.OUTSIDE;
		result.addPayment(payment);
		result.carNumberPlate = numberPlate;
		return result;
	}
}
