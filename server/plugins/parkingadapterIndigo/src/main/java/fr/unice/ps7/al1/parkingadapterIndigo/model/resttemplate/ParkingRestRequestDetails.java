package fr.unice.ps7.al1.parkingadapterIndigo.model.resttemplate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParkingRestRequestDetails {
	private int nhits;
	private List<RecordParkingDetails> records;

	public ParkingRestRequestDetails() {
	}

	public int getNhits() {
		return nhits;
	}

	public void setNhits(int nhits) {
		this.nhits = nhits;
	}

	public List<RecordParkingDetails> getRecords() {
		return records;
	}

	public void setRecords(List<RecordParkingDetails> records) {
		this.records = records;
	}
}
