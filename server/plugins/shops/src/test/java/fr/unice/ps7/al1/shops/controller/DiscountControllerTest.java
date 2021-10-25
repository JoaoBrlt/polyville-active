package fr.unice.ps7.al1.shops.controller;

import fr.unice.ps7.al1.shops.model.Discount;
import fr.unice.ps7.al1.shops.model.Shop;
import fr.unice.ps7.al1.shops.service.DiscountService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DiscountController.class)
class DiscountControllerTest {
	@Autowired
	private MockMvc mvc;

	@MockBean
	private DiscountService discountService;

	@Test
	public void getDiscounts() throws Exception {
		// Shop.
		Shop shop = new Shop("Name", "Owner", "Type", "Address", 48.858370, 2.294481);
		shop.setId(1L);

		// First discount.
		Discount discount = new Discount("Title", 1.0, false, "Condition", shop);
		discount.setId(2L);

		// Second discount.
		Discount secondDiscount = new Discount("Title 2", 1.0, false, "Condition 2", shop);
		discount.setId(3L);

		// Mock the discount service.
		List<Discount> discounts = List.of(discount, secondDiscount);
		when(discountService.findAll()).thenReturn(discounts);

		mvc
			.perform(get("/discounts"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$", hasSize(2)))
			.andExpect(jsonPath("$[0].id", is(discount.getId().intValue())))
			.andExpect(jsonPath("$[0].title", is(discount.getTitle())))
			.andExpect(jsonPath("$[0].value", is(discount.getValue())))
			.andExpect(jsonPath("$[0].isPercentage", is(discount.isPercentage())))
			.andExpect(jsonPath("$[0].condition", is(discount.getCondition())));
	}

	@Test
	public void getDiscount() throws Exception {
		// Mock the discount service.
		Shop shop = new Shop("Name", "Owner", "Type", "Address", 48.858370, 2.294481);
		Discount discount = new Discount("Title", 1.0, false, "Condition", shop);
		discount.setId(1L);
		when(discountService.findById(1L)).thenReturn(Optional.of(discount));

		mvc
			.perform(get("/discounts/1"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("id", is(discount.getId().intValue())))
			.andExpect(jsonPath("title", is(discount.getTitle())))
			.andExpect(jsonPath("value", is(discount.getValue())))
			.andExpect(jsonPath("isPercentage", is(discount.isPercentage())))
			.andExpect(jsonPath("condition", is(discount.getCondition())));
	}
}
