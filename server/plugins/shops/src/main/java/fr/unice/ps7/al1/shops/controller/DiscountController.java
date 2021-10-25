package fr.unice.ps7.al1.shops.controller;

import fr.unice.ps7.al1.shops.model.Discount;
import fr.unice.ps7.al1.shops.service.DiscountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Discount controller.
 *
 * Defines the routes for the discounts.
 */
@RestController
@RequestMapping("/discounts")
public class DiscountController {
	/**
	 * The discount service.
	 */
	private final DiscountService discountService;

	/**
	 * Constructs the discount controller.
	 * @param discountService The discount service.
	 */
	public DiscountController(DiscountService discountService) {
		this.discountService = discountService;
	}

	@GetMapping("")
	public ResponseEntity<List<Discount>> getDiscounts() {
		return ResponseEntity.ok().body(discountService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Discount> getDiscount(@PathVariable Long id) {
		// Get the discount.
		return discountService
			.findById(id)

			// Discount found.
			.map(discount -> ResponseEntity.ok().body(discount))

			// Discount not found.
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
