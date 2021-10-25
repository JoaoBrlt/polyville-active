package fr.unice.ps7.al1.parking_innovation.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.pf4j.Extension;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

@Entity @Table(name = Payment.DATA_TABLE)
public class Payment {
	public final static String DATA_TABLE = "Payment";

	@Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="payment_sequence")
	@SequenceGenerator(name = "payment_sequence", sequenceName = "payment_sequence", allocationSize=1)
	@JsonProperty("id")
	private Long paymentId;

	private Double value;

	private LocalDateTime date = LocalDateTime.now();

	private LocalDateTime validityLimit;

	@ManyToOne(optional = false)
	@JoinColumn(name="ticket_id", referencedColumnName = "id")
	@JsonIgnore
	private ParkingTicket ticket;

	public Long getTicketId() {
		return ticket==null? null : ticket.getId();
	}

	public void setTicket(ParkingTicket ticket) {
		this.ticket = ticket;
	}

	/**
	 * @return valeur du paiment en euros
	 */
	public Double getValue() {
		return value;
	}

	public LocalDateTime getValidityLimit() {
		//TODO
		if(validityLimit!=null) return validityLimit;
//		if(ticket!=null && ticket.getParking()!= null){
//			HashMap<Integer, Double> price = ticket.getParking().getPrice();
//			price.entrySet().stream().filter()
//		}
		return null;
	}

	public LocalDateTime getDate() {
		return date;
	}
}
