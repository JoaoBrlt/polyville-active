package fr.unice.ps7.al1.shops.pipe;

import fr.unice.ps7.al1.common.model.Publication;
import fr.unice.ps7.al1.common.pipe.PublicationPipe;
import fr.unice.ps7.al1.shops.service.DiscountService;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Extension
@Component
public class ShopPublicationPipe implements PublicationPipe {
	@Autowired
	private DiscountService discountService;

	@Override
	public Collection<Publication> getElements() {
		return
			discountService.findAll().stream()
			.map((p)->(Publication)p)
			.sorted(Comparator.comparing(Publication::getDate))
			.collect(Collectors.toList());
	}
}
