package fr.unice.ps7.al1.events.controller;

import fr.unice.ps7.al1.common.model.Publication;
import fr.unice.ps7.al1.events.model.Event;
import fr.unice.ps7.al1.events.model.EventPlace;
import fr.unice.ps7.al1.events.model.EventPublication;
import fr.unice.ps7.al1.events.service.EventManagerService;
import fr.unice.ps7.al1.events.service.EventPlaceService;
import fr.unice.ps7.al1.events.service.EventPublicationService;
import fr.unice.ps7.al1.events.service.EventService;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventsPublicationController.class)
class EventPublicationControllerTest {
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
		List<EventPublication> publications = new LinkedList<>(){{
			add(new EventPublication("1","",null));
			add(new EventPublication("2","",null));
		}};

		when(eventPublicationService.findAll()).thenReturn(publications);
		when(eventPublicationService.existsById(any())).thenReturn(true);


		mvc
			.perform(get("/eventsPublications"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$",hasSize(2)))
			.andExpect(jsonPath("$.[?(@.title == \"1\")]").exists())
			.andExpect(jsonPath("$.[?(@.title == \"2\")]").exists());
	}

	@Test
	void postTest() throws Exception {
		String json = "{\"id\":null, \"title\":\"test\", \"event\": {\"id\":2}}";

		when(eventPublicationService.save(any())).thenAnswer(invocation ->{
			Object[] args = invocation.getArguments();
			return (Publication) args[0];
		});
		when(eventService.findById(any())).thenReturn(Optional.of(new Event(){{setId(2L);}}));
		when(eventService.existsById(any())).thenReturn(true);
		when(eventPublicationService.existsById(any())).thenReturn(true);


		mvc
			.perform(post("/eventsPublications")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.title",is("test")))
			.andExpect(jsonPath("$.event.id",is(2)));
	}

	@Test
	void postIfNoEvent() throws Exception {
		String json = "{\"id\":null, \"title\":\"test\"}";

		when(eventPublicationService.save(any())).thenAnswer(invocation ->{
			Object[] args = invocation.getArguments();
			return (Publication) args[0];
		});
		when(eventService.existsById(any())).thenReturn(true);
		when(eventPublicationService.existsById(any())).thenReturn(true);


		mvc
			.perform(post("/eventsPublications")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}

	@Test
	void postIfEventIdNull() throws Exception {
		String json = "{\"id\":null, \"title\":\"test\", \"event\": {\"id\":null}}";

		when(eventPublicationService.save(any())).thenAnswer(invocation ->{
			Object[] args = invocation.getArguments();
			return (Publication) args[0];
		});
		when(eventService.existsById(any())).thenReturn(true);
		when(eventPublicationService.existsById(any())).thenReturn(true);


		mvc
			.perform(post("/eventsPublications")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isBadRequest());
	}
}
