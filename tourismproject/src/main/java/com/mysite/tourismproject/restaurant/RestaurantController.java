package com.mysite.tourismproject.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {
	@GetMapping("/restaurantmenu")
	public String Restaurantmenu() {
		return "restaurant/menu2";
	}
	@GetMapping("/restaurant/restaurant")
	public String Restaurant() {
		return "restaurant/restaurant";
	}
	@GetMapping("/restaurant/addrestaurant")
	public String addRestaurant() {
		return "restaurant/addrestaurant";
	}
}
