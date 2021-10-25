package fr.unice.ps7.al1.core.controller;

import fr.unice.ps7.al1.common.model.Establishment;
import fr.unice.ps7.al1.common.model.Place;
import fr.unice.ps7.al1.common.model.Publication;
import fr.unice.ps7.al1.core.service.EstablishmentService;
import fr.unice.ps7.al1.core.service.PlaceService;
import fr.unice.ps7.al1.core.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Common controller.
 * <p>
 * Defines the routes to manage common models.
 */
@RestController
@RequestMapping("/")
public class CommonController {
	@Autowired
	private PublicationService publicationService;

	@Autowired
	private EstablishmentService establishmentService;

	@Autowired
	private PlaceService placeService;

	@GetMapping("/publications")
	Collection<Publication> getPublications(){
		return publicationService.findAll();
	}

	@GetMapping("/establishments")
	Collection<Establishment> getEstablishments(){
		return establishmentService.findAll();
	}

	@GetMapping("/places")
	Collection<Place> getPlaces(){
		return placeService.findAll();
	}
}
