package fr.unice.ps7.al1.parkingadapterIndigo.model.resttemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {
	private String name;
	private double remplissage;
	private int value_free_spots;
	private int nombre_de_places_contractuelles;
	private String parking_id;
	private List<Double> geo;

	public Fields() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getRemplissage() {
		return remplissage;
	}

	public void setRemplissage(double remplissage) {
		this.remplissage = remplissage;
	}

	public int getValue_free_spots() {
		return value_free_spots;
	}

	public void setValue_free_spots(int value_free_spots) {
		this.value_free_spots = value_free_spots;
	}

	public int getNombre_de_places_contractuelles() {
		return nombre_de_places_contractuelles;
	}

	public void setNombre_de_places_contractuelles(int nombre_de_places_contractuelles) {
		this.nombre_de_places_contractuelles = nombre_de_places_contractuelles;
	}

	public String getParking_id() {
		return parking_id;
	}

	public void setParking_id(String parking_id) {
		this.parking_id = parking_id;
	}

	public List<Double> getGeo() {
		return geo;
	}

	public void setGeo(List<Double> geo) {
		this.geo = geo;
	}
}
