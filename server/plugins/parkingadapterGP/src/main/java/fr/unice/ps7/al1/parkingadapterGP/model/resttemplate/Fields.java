package fr.unice.ps7.al1.parkingadapterGP.model.resttemplate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {
	private String nom;
	private double taux_doccupation;
	private int places_restantes;
	private int capacite;
	private String id;
	private List<Double> geo_point_2d;

	public Fields() {
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getTaux_doccupation() {
		return taux_doccupation;
	}

	public void setTaux_doccupation(double taux_doccupation) {
		this.taux_doccupation = taux_doccupation;
	}

	public int getPlaces_restantes() {
		return places_restantes;
	}

	public void setPlaces_restantes(int places_restantes) {
		this.places_restantes = places_restantes;
	}

	public int getCapacite() {
		return capacite;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Double> getGeo_point_2d() {
		return geo_point_2d;
	}

	public void setGeo_point_2d(List<Double> geo_point_2d) {
		this.geo_point_2d = geo_point_2d;
	}
}
