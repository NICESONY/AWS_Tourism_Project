package com.mysite.tourismproject.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RestaurantController {
	
	
	@GetMapping("/Restaurant")
	public String Restaurant() {
		return "Restaurant/Restaurant";
	}

	

}
