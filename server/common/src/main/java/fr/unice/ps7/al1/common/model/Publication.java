package fr.unice.ps7.al1.common.model;

import org.pf4j.ExtensionPoint;

import java.time.LocalDateTime;

/**
 * Publication.
 * <p>
 * Represents a general purpose publication.
 */
public interface Publication extends ExtensionPoint, Data {
	String getTitle();
	String getContent();
	LocalDateTime getDate();
	Object getOwner();
}
