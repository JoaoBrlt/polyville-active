package fr.unice.ps7.al1.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.unice.ps7.al1.events.model.Event;
import fr.unice.ps7.al1.events.model.EventManager;
import fr.unice.ps7.al1.events.model.EventPlace;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class JsonTest {
	@Test
	void testEventJsonTransform() throws JsonProcessingException {
		EventPlace place = new EventPlace("placeTest", "AdressTest",12.2,13.5);
		EventManager manager = new EventManager("owner", "owner@yolo.fr", "02.03.21.54.65");
		Event event = new Event("title", "descib", LocalDateTime.now(), LocalDateTime.now(),place, manager);
		event.setId(12L);
		ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

		String json = mapper.writeValueAsString(event);
		System.out.println("1 ----> "+json);
		event = mapper.readValue(json, Event.class);
		System.out.println("2 ----> "+mapper.writeValueAsString(event));

		event = mapper.readValue("{" +
			"\"title\":\"title\"," +
			"\"startDate\":[2020,12,16,15,26,59,526499200]," +
			"\"endDate\":[2020,12,16,15,26,59,528499000]," +
			"\"location\":{\"name\":\"placeTest\",\"address\":\"AdressTest\",\"latitude\":12.2,\"longitude\":13.5,\"id\":null}" +
			"}", Event.class);
		System.out.println("4 ----> "+mapper.writeValueAsString(event));
	}
}
