package com.mysite.tourismproject.restaurant;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {
	@Autowired
	private RestaurantRepository restaurantRepository;
	
	public void locationcreate(Restaurant restaurant) {
		restaurantRepository.save(restaurant);
	}
	public List<Restaurant> readlist(){
		return restaurantRepository.findAll();
	}
	public Restaurant findById(Integer id) {
		Optional<Restaurant>restaurant = restaurantRepository.findById(id);
		return restaurant.get();
	}
}
