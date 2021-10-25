package fr.unice.ps7.al1.shops.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.unice.ps7.al1.shops.model.Shop;
import fr.unice.ps7.al1.shops.service.DiscountService;
import fr.unice.ps7.al1.shops.service.ShopService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ShopController.class)
class ShopControllerTest {
	@Autowired
	private MockMvc mvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private DiscountService discountService;

	@MockBean
	private ShopService shopService;

	@Test
	public void getShops() throws Exception {
		// First shop.
		Shop shop = new Shop("Name", "Owner", "Type", "Address", 48.858370, 2.294481);
		shop.setId(1L);

		// Second shop.
		Shop secondShop = new Shop("Name 2", "Owner 2", "Type 2", "Address 2", 48.858370, 2.294481);
		secondShop.setId(2L);

		// Mock the shop service.
		List<Shop> shops = List.of(shop, secondShop);
		when(shopService.findAll()).thenReturn(shops);

		mvc
			.perform(get("/shops"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].id", is(shop.getId().intValue())))
			.andExpect(jsonPath("$[0].name", is(shop.getName())))
			.andExpect(jsonPath("$[0].owner", is(shop.getOwner())))
			.andExpect(jsonPath("$[0].type", is(shop.getType())))
			.andExpect(jsonPath("$[0].address", is(shop.getAddress())))
			.andExpect(jsonPath("$[0].latitude", is(shop.getLatitude())))
			.andExpect(jsonPath("$[0].longitude", is(shop.getLongitude())));
	}

	@Test
	public void addShop() throws Exception {
		// Request shop.
		Shop requestShop = new Shop("Name", "Owner", "Type", "Address", 48.858370, 2.294481);

		// Response shop.
		Shop responseShop = new Shop("Name", "Owner", "Type", "Address", 48.858370, 2.294481);
		responseShop.setId(1L);

		// Mock the shop service.
		when(shopService.save(requestShop)).thenReturn(responseShop);

		mvc
			.perform(
				post("/shops")
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(requestShop))
			)
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("id", is(responseShop.getId().intValue())))
			.andExpect(jsonPath("name", is(responseShop.getName())))
			.andExpect(jsonPath("owner", is(responseShop.getOwner())))
			.andExpect(jsonPath("type", is(responseShop.getType())))
			.andExpect(jsonPath("address", is(responseShop.getAddress())))
			.andExpect(jsonPath("latitude", is(responseShop.getLatitude())))
			.andExpect(jsonPath("longitude", is(responseShop.getLongitude())));
	}

	@Test
	public void getShop() throws Exception {
		// Mock the shop service.
		Shop shop = new Shop("Name", "Owner", "Type", "Address", 48.858370, 2.294481);
		when(shopService.findById(1L)).thenReturn(Optional.of(shop));

		mvc
			.perform(get("/shops/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("name", is(shop.getName())))
			.andExpect(jsonPath("owner", is(shop.getOwner())))
			.andExpect(jsonPath("type", is(shop.getType())))
			.andExpect(jsonPath("address", is(shop.getAddress())))
			.andExpect(jsonPath("latitude", is(shop.getLatitude())))
			.andExpect(jsonPath("longitude", is(shop.getLongitude())));
	}
}
