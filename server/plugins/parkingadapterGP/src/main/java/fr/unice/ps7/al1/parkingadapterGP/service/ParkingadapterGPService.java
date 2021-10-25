package fr.unice.ps7.al1.parkingadapterGP.service;

import fr.unice.ps7.al1.parking.interfaces.ParkingconsumingService;
import fr.unice.ps7.al1.parking.model.Parking;
import fr.unice.ps7.al1.parkingadapterGP.model.resttemplate.Fields;
import fr.unice.ps7.al1.parkingadapterGP.model.resttemplate.ParkingRestRequestDetails;
import fr.unice.ps7.al1.parkingadapterGP.model.resttemplate.RecordParkingDetails;
import org.pf4j.Extension;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Extension
@Service
public class ParkingadapterGPService implements ParkingconsumingService {

	RestTemplate restTemplate = new RestTemplate();

	@Override
	public List<Parking> getParking() {
		List<Parking> parkingDetails = new ArrayList<>();
		final ParkingRestRequestDetails responseBody = restTemplate.getForObject("https://data.opendatasoft.com/api/records/1.0/search/?dataset=mobilites-stationnement-des-parkings-en-temps-reel@grandpoitiers", ParkingRestRequestDetails.class);
		if (responseBody != null) {
			for (RecordParkingDetails request : responseBody.getRecords()) {
				parkingDetails.add(castToParking(request.getFields()));
			}
		}

		return parkingDetails;
	}

	Parking castToParking(Fields request) {
		return new Parking(request.getNom(), "Grand poitier", this.getAddress(request.getGeo_point_2d().get(0), request.getGeo_point_2d().get(1)), request.getGeo_point_2d().get(0), request.getGeo_point_2d().get(1), request.getCapacite(), request.getPlaces_restantes(),
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
