package fr.unice.ps7.al1.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.ps7.al1.common.model.Publication;
import fr.unice.ps7.al1.events.EventsPlugin;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = EventPublication.DATA_TABLE)
public class EventPublication implements Publication {
	public final static String DATA_TABLE = "EventPublication";
	public final static String EVENT_KIND = "event_publication";
	/**
	 * The publication ID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="event_publication_sequence")
	@SequenceGenerator(name = "event_publication_sequence", sequenceName = "event_publication_sequence", allocationSize=1)
	@JsonProperty("id")
	private Long idPublication;

	/**
	 * The publication title
	 */
	private String title;

	/**
	 * The publication content
	 */
	private String content;

	/**
	 * The publication creation date
	 */
	private LocalDateTime date = LocalDateTime.now();

	/**
	 * The event linked to this publication
	 */
	@ManyToOne
	@JoinColumn(name="idEvent", nullable = false)
	private Event event;

	public EventPublication(){}

	public EventPublication(
		String title,
		String content,
		Event event
	){
		this.title=title;
		this.content=content;
		this.event=event;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getContent() {
		return content;
	}

	@Override
	public LocalDateTime getDate() {
		return date;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public EventManager getOwner() {
		return event==null ? null : event.getOwner();
	}

	public Event getEvent() {
		return event;
	}

	@Override
	public Long getId() {
		return idPublication;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public String getKind() {
		return EventPublication.EVENT_KIND;
	}

	@Override
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	public String getPluginId() {
		return EventsPlugin.PLUGIN_ID;
	}

	public void setOwner(Event event) {
		this.event = event;
	}
}
