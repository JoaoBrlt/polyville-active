package fr.unice.ps7.al1.parkingadapterGP.model.resttemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecordParkingDetails {
	private Fields fields;

	public RecordParkingDetails() {
	}

	public Fields getFields() {
		return fields;
	}

	public void setFields(Fields fields) {
		this.fields = fields;
	}
}
