package com.mysite.tourismproject.location;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;
	
	public void locationcreate(Location location) {
		locationRepository.save(location);
	}
	public List<Location> readlist(){
		return locationRepository.findAll();
	}
}
