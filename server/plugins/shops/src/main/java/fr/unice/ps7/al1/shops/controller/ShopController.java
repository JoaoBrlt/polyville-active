package fr.unice.ps7.al1.shops.controller;

import fr.unice.ps7.al1.shops.model.Discount;
import fr.unice.ps7.al1.shops.model.Shop;
import fr.unice.ps7.al1.shops.service.DiscountService;
import fr.unice.ps7.al1.shops.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Shop controller.
 *
 * Defines the routes for the shops.
 */
@RestController
@RequestMapping("/shops")
public class ShopController {
	/**
	 * The shop service.
	 */
	private final ShopService shopService;

	/**
	 * The discount service.
	 */
	private final DiscountService discountService;

	/**
	 * Constructs the shop controller.
	 * @param shopService The shop service.
	 */
	public ShopController(ShopService shopService, DiscountService discountService) {
		this.shopService = shopService;
		this.discountService = discountService;
	}

	@GetMapping("")
	public List<Shop> getShops() {
		// Get the shops.
		return shopService.findAll();
	}

	@PostMapping("")
	public ResponseEntity<Shop> addShop(@RequestBody Shop shop) {
		// Add a shop.
		return ResponseEntity.ok().body(shopService.save(shop));
	}

	@GetMapping("/{shopId}")
	public ResponseEntity<Shop> getShop(@PathVariable Long shopId) {
		// Get the shop.
		return shopService
			.findById(shopId)

			// Shop found.
			.map(shop -> ResponseEntity.ok().body(shop))

			// Shop not found.
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{shopId}")
	public ResponseEntity<Shop> updateShop(@PathVariable Long shopId, @RequestBody Shop newShop) {
		// Update the shop.
		newShop.setId(shopId);
		return ResponseEntity.ok().body(shopService.save(newShop));
	}

	@DeleteMapping("/{shopId}")
	public ResponseEntity<String> deleteShop(@PathVariable Long shopId) {
		// Get the shop.
		return shopService
			.findById(shopId)

			// Delete the shop.
			.map(shop -> {
				shopService.deleteById(shopId);
				return ResponseEntity.ok().body("The shop was deleted.");
			})

			// Shop not found.
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{shopId}/discounts")
	public ResponseEntity<List<Discount>> getDiscounts(@PathVariable Long shopId) {
		// Get the shop.
		return shopService
			.findById(shopId)

			// Get the discounts.
			.map(shop -> ResponseEntity.ok().body(discountService.findByShop(shop)))

			// Shop not found.
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/{shopId}/discounts")
	public ResponseEntity<Discount> addDiscount(@PathVariable Long shopId, @RequestBody Discount discount) {
		// Get the shop.
		return shopService
			.findById(shopId)

			// Add the discount.
			.map(shop -> {
				discount.setOwner(shop);
				return ResponseEntity.ok().body(discountService.save(discount));
			})

			// Shop not found.
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@GetMapping("/{shopId}/discounts/{id}")
	public ResponseEntity<Discount> getDiscount(@PathVariable Long shopId, @PathVariable Long id) {
		// Get the shop.
		return shopService
			.findById(shopId)

			// Get the discount.
			.map(shop ->
				discountService
					.findById(id)

					// Discount found.
					.map(discount -> ResponseEntity.ok().body(discount))

					// Discount not found.
					.orElseGet(() -> ResponseEntity.notFound().build())
			)

			// Shop not found.
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{shopId}/discounts/{id}")
	public ResponseEntity<Discount> updateDiscount(@PathVariable Long shopId, @PathVariable Long id, @RequestBody Discount newDiscount) {
		// Get the shop.
		return shopService
			.findById(shopId)

			// Get the discount.
			.map(shop ->
				discountService
					.findById(id)

					// Update the discount.
					.map(discount -> {
						newDiscount.setId(id);
						newDiscount.setOwner(shop);
						return ResponseEntity.ok().body(discountService.save(newDiscount));
					})

					// Discount not found.
					.orElseGet(() -> ResponseEntity.notFound().build())
			)

			// Shop not found.
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{shopId}/discounts/{id}")
	public ResponseEntity<String> deleteDiscount(@PathVariable Long shopId, @PathVariable Long id) {
		// Get the shop.
		return shopService
			.findById(shopId)

			// Get the discount.
			.map(shop ->
				discountService
					.findById(id)

					// Delete the discount.
					.map(discount -> {
						discountService.deleteById(id);
						return ResponseEntity.ok().body("The discount was deleted.");
					})

					// Discount not found.
					.orElseGet(() -> ResponseEntity.notFound().build())
			)

			// Shop not found.
			.orElseGet(() -> ResponseEntity.notFound().build());
	}
}
