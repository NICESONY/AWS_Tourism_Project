package com.mysite.tourismproject.restaurant;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	Optional<Restaurant> findById(Integer id);
}
