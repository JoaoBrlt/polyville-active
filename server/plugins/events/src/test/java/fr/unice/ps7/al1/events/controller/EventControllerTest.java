package fr.unice.ps7.al1.events.controller;

import fr.unice.ps7.al1.events.model.Event;
import fr.unice.ps7.al1.events.model.EventPlace;
import fr.unice.ps7.al1.events.service.EventManagerService;
import fr.unice.ps7.al1.events.service.EventPlaceService;
import fr.unice.ps7.al1.events.service.EventPublicationService;
import fr.unice.ps7.al1.events.service.EventService;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(EventsController.class)
class EventControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private EventService eventService;
	@MockBean
	private EventPlaceService eventPlaceService;
	@MockBean
	private EventManagerService eventManagerService;
	@MockBean
	private EventPublicationService eventPublicationService;

	@Test
	void afterAddShouldReturn() throws Exception {
		List<Event> events = new LinkedList<>(){{
			add(new Event("1","",null, null, null, null));
			add(new Event("2","",null, null, null, null));
		}};

		when(eventService.findAll()).thenReturn(events);


		mvc
			.perform(get("/events"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$",hasSize(2)))
			.andExpect(jsonPath("$.[?(@.title == \"1\")]").exists())
			.andExpect(jsonPath("$.[?(@.title == \"2\")]").exists());
	}

	@Test
	void postTest() throws Exception {
		String json = "{\"title\":\"monEvent\",\"description\":\"descib\",\"startDate\":[2020,12,18,9,33,28,515202200],\"endDate\":[2020,12,18,9,33,28,517201500],\"location\":{\"name\":\"placeTest\",\"address\":\"AdressTest\",\"latitude\":12.2,\"longitude\":13.5,\"pluginId\":\"events\",\"kind\":\"event_place\",\"id\":null},\"date\":[2020,12,18,9,33,28,517201500],\"id\":null,\"pluginId\":\"events\",\"kind\":\"event\",\"content\":\"descib\"}";

		when(eventService.save(any())).thenAnswer(invocation ->{
				Object[] args = invocation.getArguments();
				return (Event) args[0];
			});
		when(eventPlaceService.save(any())).thenReturn(new EventPlace(){{setId(12L);}});


		mvc
			.perform(post("/events")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.title",is("monEvent")))
			.andExpect(jsonPath("$.location.id",is(12)))
			.andExpect(jsonPath("$.owner").value(IsNull.nullValue()));
	}
}
