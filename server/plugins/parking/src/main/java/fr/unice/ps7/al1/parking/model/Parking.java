package fr.unice.ps7.al1.parking.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.ps7.al1.common.model.Establishment;
import org.pf4j.Extension;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.HashMap;
import javax.persistence.*;

/**
 * Parking.
 * <p>
 * Represents a parking.
 */
@Entity
@Extension
public class Parking implements Establishment {

	@JsonIgnore
	private final static ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * The parking identifier
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="parking_sequence")
	@SequenceGenerator(name = "parking_sequence", sequenceName = "parking_sequence", allocationSize=1)
	private Long id;

	/**
	 * The parking name.
	 */
	private String name;

	/**
	 * The parking owner.
	 */
	private String owner;

	/**
	 * The parking address.
	 */
	private String address;

	/**
	 * The parking latitude.
	 */
	private Double latitude;

	/**
	 * The parking longitude.
	 */
	private Double longitude;

	/**
	 * The parking capacity
	 * (total places in the parking)
	 */
	private int capacity;

	/**
	 * The parking number of
	 * available places
	 */
	private int availablePlaces;

	/**
	 * The price of the parking
	 *
	 * It is a map of price list on json format:
	 * 	ex: {"minutes":priceForThisTime, ...}
	 */
	private String price;

	/**
	 * True if is an outside parking
	 */
	private boolean outsideParking = false;

	/**
	 * Default constructor.
	 */
	public Parking() {
	}

	/**
	 * Constructs a Parking.
	 *
	 * @param name            The parking name.
	 * @param owner           The parking owner.
	 * @param address         The parking address.
	 * @param latitude        The parking latitude.
	 * @param longitude       The parking longitude
	 * @param capacity        The parking capacity
	 * @param availablePlaces The parking number of available places
	 */
	public Parking(
		String name,
		String owner,
		String address,
		double latitude,
		double longitude,
		int capacity,
		int availablePlaces,
		String price
	) {
		this.name = name;
		this.owner = owner;
		this.address = address;
		this.latitude = latitude;
		this.capacity = capacity;
		this.longitude = longitude;
		this.availablePlaces = availablePlaces;
		this.price = price;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getKind() {
		return "parking";
	}

	@Override
	public String getPluginId() {
		return "parking";
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getAvailablePlaces() {
		return availablePlaces;
	}

	public void setAvailablePlaces(int availablePlaces) {
		this.availablePlaces = availablePlaces;
	}

	public HashMap<Integer, Double> getPrice() {
		try {
			TypeReference<HashMap<Integer, Double>> typeRef
				= new TypeReference<HashMap<Integer, Double>>() {};
			return objectMapper.readValue(this.price, typeRef);
		} catch (Exception e) {
			System.out.println("get: "+ e);
			return new HashMap<>();
		}
	}

	public void setPrice(HashMap<Integer, Double> price) {
		try {
			this.price = objectMapper.writeValueAsString(price);
		} catch (Exception e) {
			System.out.println("set : "+e);
			this.price = "  \"price\": {}";
		}
	}

	@JsonIgnore
	public String getPriceString() {
		return this.price;
	}

	@Override
	public String toString() {
		return "Parking{" +
			"id=" + id +
			", name='" + name + '\'' +
			", owner='" + owner + '\'' +
			", address='" + address + '\'' +
			", latitude=" + latitude +
			", longitude=" + longitude +
			", capacity=" + capacity +
			", availablePlaces=" + availablePlaces +
			", price=" + price +
			'}';
	}

	public void setLikeOutsideParking() {
		this.outsideParking = true;
	}

	public boolean getOutsideParking() {
		return outsideParking;
	}
}
