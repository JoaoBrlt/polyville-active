package fr.unice.ps7.al1.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.ps7.al1.common.model.Publication;
import fr.unice.ps7.al1.events.EventsPlugin;
import org.pf4j.Extension;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Event.
 * <p>
 * Represents a general purpose event.
 */
@Entity @Table(name = Event.DATA_TABLE)
@Extension
public class Event implements Publication {
	public final static String DATA_TABLE = "Events";
	public final static String EVENT_KIND = "event";

	/**
	 * The event identifier.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="event_sequence")
	@SequenceGenerator(name = "event_sequence", sequenceName = "event_sequence", allocationSize=1)
	@JsonProperty("id")
	private Long idEvent;

	/**
	 * The event title.
	 */
	private String title;

	/**
	 * The event description.
	 */
	private String description;

	/**
	 * The event starting date.
	 */
	private LocalDateTime startDate;

	/**
	 * The event ending date.
	 */
	private LocalDateTime endDate;

	/**
	 * The creation date.
	 */
	@JsonProperty("date")
	private LocalDateTime createdAt;

	/**
	 * The location of the event
	 */
	@ManyToOne()
	@JoinColumn(name="idPlace")
	private EventPlace location;

	/**
	 * The location of the event
	 */
	@ManyToOne
	@JoinColumn(name="idManager")
	private EventManager owner;

	/**
	 * Default constructor.
	 */
	public Event() {}

	/**
	 * Constructs a discount.
	 * @param title The event title.
	 * @param description The event content.
	 * @param start The event starting date.
	 * @param end The event ending date.
	 * @param location The location'Id of the event.
	 */
	public Event(
		String title,
		String description,
		LocalDateTime start,
		LocalDateTime end,
		EventPlace location,
		EventManager manager
	) {
		this.title = title;
		this.description = description;
		this.startDate = start;
		this.endDate = end;
		this.createdAt = LocalDateTime.now();
		this.location = location;
		this.owner = manager;
	}


	public Long getId() {
		return idEvent;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public String getKind() {
		return EVENT_KIND;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public String getPluginId() {
		return EventsPlugin.PLUGIN_ID;
	}

	public void setId(Long id) {
		this.idEvent = id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public String getContent() {
		String result = "Evenement : "+title+"\n";
		if(startDate!=null) {
			if(endDate!=null) {
				result+="du " + startDate.toLocalDate()+" a "+startDate.toLocalTime()+" au "+endDate.toLocalDate()+" a "+endDate.toLocalTime()+"\n";
			} else {
				result+="le " + startDate.toLocalDate()+" a "+startDate.toLocalTime()+"\n";
			}
		} else if(endDate!=null){
			result+="jusqu'au "+endDate.toLocalDate()+" a "+endDate.toLocalTime()+"\n";
		}
		result+=description+"\n";
		return result;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public LocalDateTime getDate() {
		return createdAt;
	}

	@Override
	public EventManager getOwner() {
		return owner;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public EventPlace getLocation() {
		return location;
	}

	public void setOwner(EventManager owner) {
		this.owner = owner;
	}

	public void setLocation(EventPlace location) {
		this.location = location;
	}
}
