package fr.unice.ps7.al1.events;

import fr.unice.ps7.al1.events.controller.EventManagerController;
import fr.unice.ps7.al1.events.controller.EventsController;
import fr.unice.ps7.al1.events.controller.EventsPlaceController;
import fr.unice.ps7.al1.events.controller.EventsPublicationController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EventsApplicationTest {
	/////// Application ///////
	@Test
	void contextLoads() {
		assertTrue(true);
	}

	/////// Event controller ///////
	@Autowired
	EventsController eController;

	@Test
	void eventControllerLoad(){
		assertThat(eController).isNotNull();
	}

	/////// Publication controller ///////
	@Autowired
	EventsPublicationController pController;

	@Test
	void publicationControllerLoad(){
		assertThat(pController).isNotNull();
	}

	/////// Place controller ///////
	@Autowired
	EventsPlaceController plController;

	@Test
	void placeControllerLoad(){
		assertThat(plController).isNotNull();
	}

	/////// Manager controller ///////
	@Autowired
	EventManagerController mController;

	@Test
	void managerControllerLoad(){
		assertThat(mController).isNotNull();
	}
}
