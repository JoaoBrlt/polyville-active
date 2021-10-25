package fr.unice.ps7.al1.common.model;

import org.pf4j.ExtensionPoint;

/**
 * Place.
 * <p>
 * Represents a general purpose place in the map.
 */
public interface Place extends ExtensionPoint, Data {
	String getName();
	String getAddress();
	Double getLatitude();
	Double getLongitude();
}
