package fr.unice.ps7.al1.shops.service;

import fr.unice.ps7.al1.shops.model.Shop;
import fr.unice.ps7.al1.shops.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Shop services.
 *
 * Defines methods to store and retrieve shops.
 */
@Service
public class ShopService {
	/**
	 * The shop repository.
	 */
	private final ShopRepository shopRepository;

	/**
	 * Constructs a shop service.
	 * @param shopRepository The shop repository.
	 */
	public ShopService(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}

	public List<Shop> findAll() {
		return shopRepository.findAll();
	}

	public boolean existsById(Long id) {
		return shopRepository.existsById(id);
	}

	public Optional<Shop> findById(Long id) {
		return shopRepository.findById(id);
	}

	public Shop save(Shop shop) {
		return shopRepository.save(shop);
	}

	public void deleteById(Long id) {
		shopRepository.deleteById(id);
	}
}
