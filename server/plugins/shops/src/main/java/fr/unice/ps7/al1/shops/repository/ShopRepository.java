package fr.unice.ps7.al1.shops.repository;

import fr.unice.ps7.al1.shops.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
}
