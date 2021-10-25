package fr.unice.ps7.al1.parking.pipe;

import fr.unice.ps7.al1.common.model.Establishment;
import fr.unice.ps7.al1.common.model.Place;
import fr.unice.ps7.al1.common.pipe.EstablishmentPipe;
import fr.unice.ps7.al1.parking.service.ParkingService;
import org.pf4j.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Extension @Component
public class ParkingEstablishmentPipe implements EstablishmentPipe {
	@Autowired
	private ParkingService parkingService;

	@Override
	public Collection<Establishment> getElements() {
		return parkingService.findAll()
			.stream()
			.map((p)->(Establishment)p)
			.collect(Collectors.toList());
	}
}
