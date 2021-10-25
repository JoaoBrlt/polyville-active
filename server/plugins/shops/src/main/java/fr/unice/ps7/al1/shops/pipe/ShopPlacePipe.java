package fr.unice.ps7.al1.shops.pipe;


import fr.unice.ps7.al1.common.model.Place;
import fr.unice.ps7.al1.common.pipe.PlacePipe;
import fr.unice.ps7.al1.shops.service.ShopService;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Extension
@Component
public class ShopPlacePipe implements PlacePipe {
	@Autowired
	private ShopService shopService;

	@Override
	public Collection<Place> getElements() {
		return shopService.findAll().stream()
			.map((p)->(Place)p)
			.collect(Collectors.toList());
	}
}
