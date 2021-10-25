package fr.unice.ps7.al1.parking.service;

import fr.unice.ps7.al1.parking.interfaces.ParkingconsumingService;
import fr.unice.ps7.al1.parking.model.Parking;
import fr.unice.ps7.al1.parking.repository.ParkingRepository;

import org.pf4j.PluginWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("parkingService")
public class ParkingService {
	@Autowired
	private ParkingRepository repository;

	@Lazy
	@Autowired
	private PluginWrapper pluginWrapper;

	public List<Parking> findAll() {
		return repository.findAll();
	}

	public void consumeAPI(){
		List<ParkingconsumingService> parkingConsumingServices = pluginWrapper
			.getPluginManager()
			.getExtensions(ParkingconsumingService.class);

		List<Parking> newParking = new ArrayList<>();
		for (ParkingconsumingService parkingconsumingService : parkingConsumingServices) {
			newParking.addAll(parkingconsumingService.getParking());
		}
		for (Parking parking : newParking) {
			Optional<Parking> oldParking = repository.findByName(parking.getName());
			if (oldParking.isPresent()) {
				oldParking.get().setOwner(parking.getOwner());
				oldParking.get().setAddress(parking.getAddress());
				oldParking.get().setLatitude(parking.getLatitude());
				oldParking.get().setLongitude(parking.getLongitude());
				oldParking.get().setCapacity(parking.getCapacity());
				oldParking.get().setAvailablePlaces(parking.getAvailablePlaces());
				oldParking.get().setPrice(parking.getPrice());
				repository.save(oldParking.get());
			} else {
				repository.save(new Parking(parking.getName(),
					parking.getOwner(),
					parking.getAddress(),
					parking.getLatitude(),
					parking.getLongitude(),
					parking.getCapacity(),
					parking.getAvailablePlaces(),
					parking.getPriceString()));
			}
		}
	}

	public Optional<Parking> findById(Long id) {
		return repository.findById(id);
	}

	public Parking save(Parking toRegister) {
		return repository.save(toRegister);
	}

	public void delete(Parking toDelete) {

	}

	public void deleteById(Long id) {
		repository.deleteById(id);
	}

	public boolean existsById(Long id) {
		return repository.existsById(id);
	}

	public Optional<Integer> incrementFreePlaceByParkingId(Long id){
		return incrementFreePlaceByParkingId(id, 1);
	}

	public Optional<Integer> decrementFreePlaceByParkingId(Long id){
		return incrementFreePlaceByParkingId(id, -1);
	}

	public Optional<Integer> incrementFreePlaceByParkingId(Long id, int incr){
		return repository.findById(id).map((parking)->{
			int newPlace = parking.getAvailablePlaces()+incr;
			if (newPlace<0 || newPlace>parking.getCapacity())
				throw new RuntimeException("incrementation cancel : out of bound");
			parking.setAvailablePlaces(newPlace);
			return newPlace;
		});
	}
}
