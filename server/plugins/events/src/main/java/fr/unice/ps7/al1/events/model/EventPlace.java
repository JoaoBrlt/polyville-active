package fr.unice.ps7.al1.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.ps7.al1.common.model.Place;
import fr.unice.ps7.al1.events.EventsPlugin;

import javax.persistence.*;

@Entity @Table(name=EventPlace.DATA_TABLE)
public class EventPlace implements Place {
	public final static String DATA_TABLE = "EventPlaces";
	public final static String EVENT_PLACE_KIND = "event_place";

	/**
	 * The event identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="event_place_sequence")
	@SequenceGenerator(name = "event_place_sequence", sequenceName = "event_place_sequence", allocationSize=1)
	@JsonProperty("id")
	private Long idPlace;

	/**
	 * The place name
	 */
	private String name;

	/**
	 * The address of the place
	 */
	private String address;

	/**
	 * The latitude of the place
	 */
	private Double latitude;

	/**
	 * The longitude of the place
	 */
	private Double longitude;

	/**
	 * The default constructor
	 */
	public EventPlace(){}

	/**
	 * The constructor
	 * @param name Name of the place
	 * @param address Address of the place
	 * @param latitude Latitude of the place
	 * @param longitude Longitude of the place
	 */
	public EventPlace(
		String name,
		String address,
		Double latitude,
		Double longitude
	){
		this.name=name;
		this.address=address;
		this.latitude=latitude;
		this.longitude=longitude;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAddress() {
		return this.address;
	}

	@Override
	public Double getLatitude() {
		return latitude;
	}

	@Override
	public Double getLongitude() {
		return longitude;
	}

	@Override
	public Long getId() {
		return idPlace;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public String getKind() {
		return EVENT_PLACE_KIND;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public String getPluginId() {
		return EventsPlugin.PLUGIN_ID;
	}

	public void setId(Long id) {
		this.idPlace = id;
	}
}
