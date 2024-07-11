package com.mysite.tourismproject.hotel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HotelController {
	
	
	@GetMapping("/Hotel")
	public String Hotel() {
		return "hotel/Hotel";
	}

	

}
