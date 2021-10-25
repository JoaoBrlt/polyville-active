package fr.unice.ps7.al1.shops.service;

import fr.unice.ps7.al1.shops.model.Discount;
import fr.unice.ps7.al1.shops.model.Shop;
import fr.unice.ps7.al1.shops.repository.DiscountRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Discount service.
 *
 * Defines methods to store and retrieve discounts.
 */
@Service
public class DiscountService {
	/**
	 * The discount repository.
	 */
	private final DiscountRepository discountRepository;

	/**
	 * Constructs a discount service.
	 * @param discountRepository The discount repository.
	 */
	public DiscountService(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	public List<Discount> findAll() {
		return discountRepository.findAll();
	}

	public List<Discount> findByShop(Shop shop) {
		return discountRepository.findByShop(shop, Sort.by("createdAt"));
	}

	public boolean existsById(Long id) {
		return discountRepository.existsById(id);
	}

	public Optional<Discount> findById(Long id) {
		return discountRepository.findById(id);
	}

	public Discount save(Discount discount) {
		return discountRepository.save(discount);
	}

	public void deleteById(Long id) {
		discountRepository.deleteById(id);
	}
}
