package fr.unice.ps7.al1.parking.pipe;

import fr.unice.ps7.al1.common.model.Place;
import fr.unice.ps7.al1.common.model.Publication;
import fr.unice.ps7.al1.common.pipe.PlacePipe;
import fr.unice.ps7.al1.parking.service.ParkingService;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Extension @Component
public class ParkingPlacePipe implements PlacePipe {
	@Autowired
	private ParkingService parkingService;

	@Override
	public Collection<Place> getElements() {
		return parkingService.findAll()
			.stream()
			.map((p)->(Place)p)
			.collect(Collectors.toList());
	}
}
