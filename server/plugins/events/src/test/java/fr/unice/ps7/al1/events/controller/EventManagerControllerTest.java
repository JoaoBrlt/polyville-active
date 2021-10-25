package fr.unice.ps7.al1.events.controller;

import fr.unice.ps7.al1.events.model.Event;
import fr.unice.ps7.al1.events.model.EventManager;
import fr.unice.ps7.al1.events.service.EventManagerService;
import fr.unice.ps7.al1.events.service.EventPlaceService;
import fr.unice.ps7.al1.events.service.EventPublicationService;
import fr.unice.ps7.al1.events.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventManagerController.class)
class EventManagerControllerTest {
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
		List<EventManager> managers = new LinkedList<>(){{
			add(new EventManager("1","",""));
			add(new EventManager("2","",""));
		}};

		when(eventManagerService.findAll()).thenReturn(managers);


		mvc
			.perform(get("/eventsManagers"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$",hasSize(2)))
			.andExpect(jsonPath("$.[?(@.name == \"1\")]").exists())
			.andExpect(jsonPath("$.[?(@.name == \"2\")]").exists());
	}
}
