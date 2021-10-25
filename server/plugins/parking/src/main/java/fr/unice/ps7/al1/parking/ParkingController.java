package fr.unice.ps7.al1.parking;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fr.unice.ps7.al1.parking.model.Parking;
import fr.unice.ps7.al1.parking.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Parking controller.
 * <p>
 * Defines the routes of the parking plugin.
 */
@RestController
@RequestMapping("/parking")
public class ParkingController {
	@Autowired
	private ParkingService parkingService;

	private ObjectMapper om = new ObjectMapper();

	@GetMapping("")
	public List<Parking> getParking() {
		parkingService.consumeAPI();
		return parkingService.findAll();
	}

	@PostMapping("")
	public ResponseEntity<Parking> newParking(@RequestBody Parking newParking) {
		return ResponseEntity.ok().body(parkingService.save(newParking));
	}

	@GetMapping("/{id}")
	public Optional<Parking> getParkingById(@PathVariable(value = "id") Long id) {
		return parkingService.findById(id);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteParking(@PathVariable(value = "id") Long id) {
		return parkingService.findById(id)
			.map(parking -> {
				parkingService.deleteById(id);
				return ResponseEntity.ok().body("The parking was deleted.");
			})
			.orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/capacity/{id}")
	public ResponseEntity<String> updateCapacityParking(@PathVariable(value = "id") Long id, @RequestBody Map<String, Integer> capacity) {
		Optional<Parking> parking = parkingService.findById(id);
		if (parking.isPresent()) {
			parking.get().setCapacity(capacity.get("capacity"));
			parkingService.save(parking.get());
			return ResponseEntity.ok().body("The parking capacity was updated to " + parking.get().getCapacity() + ".");
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Parking> updateParking(@PathVariable(value = "id") Long id, @RequestBody String parking) throws IOException {
		Optional<Parking> optParking = parkingService.findById(id);
		if (optParking.isPresent()) {
			ObjectReader updater = om.readerForUpdating(optParking.get());
			ObjectNode rootNode = om.readTree(om.createParser(parking));
			rootNode.remove("kind");
			rootNode.remove("pluginId");
			Parking merged = updater.readValue(rootNode);
			return ResponseEntity.ok().body(parkingService.save(merged));
		}
		return ResponseEntity.notFound().build();
	}
}
