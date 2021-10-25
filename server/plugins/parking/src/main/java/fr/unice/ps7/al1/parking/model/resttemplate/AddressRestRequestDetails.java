package fr.unice.ps7.al1.parking.model.resttemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressRestRequestDetails {
	private List<Feature> features;

	public AddressRestRequestDetails() {
	}

	public List<Feature> getFeatures() {
		return features;
	}
}
