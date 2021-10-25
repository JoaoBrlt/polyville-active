package fr.unice.ps7.al1.parkingadapterIndigo.service;

import fr.unice.ps7.al1.parking.interfaces.ParkingconsumingService;
import fr.unice.ps7.al1.parking.model.Parking;
import fr.unice.ps7.al1.parkingadapterIndigo.model.resttemplate.Fields;
import fr.unice.ps7.al1.parkingadapterIndigo.model.resttemplate.ParkingRestRequestDetails;
import fr.unice.ps7.al1.parkingadapterIndigo.model.resttemplate.RecordParkingDetails;
import org.pf4j.Extension;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Extension
@Service
public class ParkingadapterIndigoService implements ParkingconsumingService {

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Parking> getParking() {
		List<Parking> parkingDetails = new ArrayList<>();
		final ParkingRestRequestDetails responseBody = restTemplate.getForObject("https://data.opendatasoft.com/api/records/1.0/search/?dataset=park-indigo-disponibilite-temps-reel@issy-les-moulineaux", ParkingRestRequestDetails.class);
		if (responseBody != null) {
			for (RecordParkingDetails request : responseBody.getRecords()) {
				parkingDetails.add(castToParking(request.getFields()));
			}
		}

		return parkingDetails;
	}

	Parking castToParking(Fields request) {
		return new Parking(request.getName(), "Indigo", this.getAddress(request.getGeo().get(0), request.getGeo().get(1)), request.getGeo().get(0), request.getGeo().get(1), request.getNombre_de_places_contractuelles(), request.getValue_free_spots(),
			"{\n" +
				"        \"15\": 0.2,\n" +
				"        \"20\": 0.3,\n" +
				"        \"30\": 0.5,\n" +
				"        \"60\": 1.1,\n" +
				"        \"120\": 2.1,\n" +
				"        \"180\": 3.0,\n" +
				"        \"270\": 4.0,\n" +
				"        \"540\": 25.0\n" +
				"    }");
	}
}
