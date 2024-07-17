package com.mysite.tourismproject.restaurant;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public Location getLocationById(Integer id) {
        return locationRepository.findById(id).orElse(null);
    }
    
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
}