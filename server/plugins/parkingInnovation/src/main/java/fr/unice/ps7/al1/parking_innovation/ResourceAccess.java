package fr.unice.ps7.al1.parking_innovation;

import fr.unice.ps7.al1.parking_innovation.model.ParkingTicket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ResourceAccess {
	private static String host;

	private static String ticketPrefix;

	private static String ticketSuffix;

	ResourceAccess(
		@Value("${website.host}"				)  	String host,
		@Value("${website.path.ticket-prefix}"	)  	String ticketPrefix,
		@Value("${website.path.ticket-suffix}"	)	String ticketSuffix
	){
		ResourceAccess.host = host;
		ResourceAccess.ticketPrefix = ticketPrefix;
		ResourceAccess.ticketSuffix = ticketSuffix;
	}

	public static String generateLinkToClient(ParkingTicket parkingTicket) {
		String link = ResourceAccess.host.endsWith("/") ? ResourceAccess.host : ResourceAccess.host.replaceAll("/$","");

		if (ResourceAccess.ticketPrefix!=null && !ResourceAccess.ticketPrefix.isEmpty())
			link= link+(ResourceAccess.ticketPrefix.startsWith("/") ? ResourceAccess.ticketPrefix:"/"+ResourceAccess.ticketPrefix);

		link= link+(link.endsWith("/") ? parkingTicket.getId():"/"+parkingTicket.getId());

		if (ResourceAccess.ticketSuffix!=null && !ResourceAccess.ticketSuffix.isEmpty())
			link= link+"/"+ResourceAccess.ticketSuffix;
		return link;
	}
}
