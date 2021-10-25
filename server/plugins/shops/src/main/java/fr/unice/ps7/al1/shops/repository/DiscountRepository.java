package fr.unice.ps7.al1.shops.repository;

import fr.unice.ps7.al1.shops.model.Discount;
import fr.unice.ps7.al1.shops.model.Shop;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {
	List<Discount> findByShop(Shop shop, Sort sort);
}
