package fr.unice.ps7.al1.common.model;

import org.pf4j.ExtensionPoint;

public interface Data extends ExtensionPoint {
	Long getId();
	String getKind();
	String getPluginId();
}
