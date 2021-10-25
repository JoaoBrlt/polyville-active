package fr.unice.ps7.al1.parking.interfaces;

import fr.unice.ps7.al1.parking.model.Parking;
import fr.unice.ps7.al1.parking.model.resttemplate.AddressRestRequestDetails;
import org.pf4j.ExtensionPoint;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public interface ParkingconsumingService extends ExtensionPoint {
	RestTemplate restTemplate = new RestTemplate();

	List<Parking> getParking();

	default String getAddress(double latitude, double longitude) {
		final AddressRestRequestDetails responseBody = restTemplate.getForObject("https://api-adresse.data.gouv.fr/reverse/?lon=" + longitude + "&lat=" + latitude, AddressRestRequestDetails.class);
		if (responseBody != null && !responseBody.getFeatures().isEmpty())
			return responseBody.getFeatures().get(0).getProperties().getLabel();
		else
			return "Address";
	}
}
