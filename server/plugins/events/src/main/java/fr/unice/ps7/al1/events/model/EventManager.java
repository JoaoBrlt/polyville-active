package fr.unice.ps7.al1.events.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.ps7.al1.common.model.Data;
import fr.unice.ps7.al1.events.EventsPlugin;

import javax.persistence.*;

@Entity @Table(name = EventManager.DATA_TABLE)
public class EventManager implements Data {
	public final static String DATA_TABLE = "EventManager";
	public final static String EVENT_KIND = "event_manager";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="event_manager_sequence")
	@SequenceGenerator(name = "event_manager_sequence", sequenceName = "event_manager_sequence", allocationSize=1)
	@JsonProperty("id")
	private Long idManager;

	private String name;

	private String email;

	private String tel;

	public EventManager(){}

	public EventManager(
		String name,
		String email,
		String tel
	){
		this.name= name;
		this.tel=tel;
		this.email=email;
	}

	public String getName() {
		return name;
	}

	public Long getId() {
		return idManager;
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

	public String getEmail() {
		return email;
	}

	public String getTel() {
		return tel;
	}
}
