package fr.unice.ps7.al1.parking;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.ps7.al1.parking.model.Parking;
import fr.unice.ps7.al1.parking.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ParkingController.class)
class ParkingControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ParkingService parkingService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void getParking() throws Exception {
		Parking parking = new Parking("name", "owner", "address",
			0.589652,
			36.565856,
			758,
			236,
			"{\n" +
				"        \"15\": 0.2,\n" +
				"        \"20\": 0.3,\n" +
				"        \"30\": 0.5,\n" +
				"        \"60\": 1.1,\n" +
				"        \"120\": 2.1,\n" +
				"        \"180\": 3.0,\n" +
				"        \"270\": 4.0,\n" +
				"        \"540\": 25.0\n" +
				"    }");
		List<Parking> parkings = List.of(parking);
		when(parkingService.findAll()).thenReturn(parkings);

		mvc
			.perform(get("/parking"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].name", is(parking.getName())))
			.andExpect(jsonPath("$[0].owner", is(parking.getOwner())))
			.andExpect(jsonPath("$[0].address", is(parking.getAddress())))
			.andExpect(jsonPath("$[0].latitude", is(parking.getLatitude())))
			.andExpect(jsonPath("$[0].longitude", is(parking.getLongitude())))
			.andExpect(jsonPath("$[0].capacity", is(parking.getCapacity())))
			.andExpect(jsonPath("$[0].availablePlaces", is(parking.getAvailablePlaces())))
			.andExpect(jsonPath("$[0].price[\"15\"]", is(parking.getPrice().get(15))))
			.andExpect(jsonPath("$[0].price[\"540\"]", is(parking.getPrice().get(540))));
	}

	@Test
	void newParking() throws Exception {
		Parking parkingRequest = new Parking("name", "owner", "address",
			0.589652,
			36.565856,
			758,
			236,
			"{\n" +
				"        \"15\": 0.2,\n" +
				"        \"20\": 0.3,\n" +
				"        \"30\": 0.5,\n" +
				"        \"60\": 1.1,\n" +
				"        \"120\": 2.1,\n" +
				"        \"180\": 3.0,\n" +
				"        \"270\": 4.0,\n" +
				"        \"540\": 25.0\n" +
				"    }");
		Parking parkingResponse = new Parking("name", "owner", "address",
			0.589652,
			36.565856,
			758,
			236,
			"{\n" +
				"        \"15\": 0.2,\n" +
				"        \"20\": 0.3,\n" +
				"        \"30\": 0.5,\n" +
				"        \"60\": 1.1,\n" +
				"        \"120\": 2.1,\n" +
				"        \"180\": 3.0,\n" +
				"        \"270\": 4.0,\n" +
				"        \"540\": 25.0\n" +
				"    }");
		parkingResponse.setId(1L);
		when(parkingService.save(Mockito.any(Parking.class))).thenReturn(parkingResponse);

		mvc
			.perform(
				post("/parking")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(parkingRequest))
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("id", is(notNullValue())))
			.andExpect(jsonPath("name", is(parkingResponse.getName())))
			.andExpect(jsonPath("owner", is(parkingResponse.getOwner())))
			.andExpect(jsonPath("address", is(parkingResponse.getAddress())))
			.andExpect(jsonPath("latitude", is(parkingResponse.getLatitude())))
			.andExpect(jsonPath("longitude", is(parkingResponse.getLongitude())))
			.andExpect(jsonPath("capacity", is(parkingResponse.getCapacity())))
			.andExpect(jsonPath("availablePlaces", is(parkingResponse.getAvailablePlaces())))
			.andExpect(jsonPath("price[\"15\"]", is(parkingResponse.getPrice().get(15))))
			.andExpect(jsonPath("price[\"540\"]", is(parkingResponse.getPrice().get(540))));
	}

	@Test
	void getParkingById() throws Exception {
		Parking parking = new Parking("name", "owner", "address",
			0.589652,
			36.565856,
			758,
			236,
			"{\n" +
				"        \"15\": 0.2,\n" +
				"        \"20\": 0.3,\n" +
				"        \"30\": 0.5,\n" +
				"        \"60\": 1.1,\n" +
				"        \"120\": 2.1,\n" +
				"        \"180\": 3.0,\n" +
				"        \"270\": 4.0,\n" +
				"        \"540\": 25.0\n" +
				"    }");
		when(parkingService.findById(anyLong())).thenReturn(java.util.Optional.of(parking));

		mvc
			.perform(get("/parking/2"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("name", is(parking.getName())))
			.andExpect(jsonPath("owner", is(parking.getOwner())))
			.andExpect(jsonPath("address", is(parking.getAddress())))
			.andExpect(jsonPath("latitude", is(parking.getLatitude())))
			.andExpect(jsonPath("longitude", is(parking.getLongitude())))
			.andExpect(jsonPath("capacity", is(parking.getCapacity())))
			.andExpect(jsonPath("availablePlaces", is(parking.getAvailablePlaces())))
			.andExpect(jsonPath("price[\"15\"]", is(parking.getPrice().get(15))))
			.andExpect(jsonPath("price[\"540\"]", is(parking.getPrice().get(540))));
	}

	@Test
	void deleteParking() {
	}

	@Test
	void updateCapacityParking() {
	}

	@Test
	void updateParking() {
	}

	@Test
	void getForObjectOperation() {
	}
}
