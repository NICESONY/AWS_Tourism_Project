package com.mysite.tourismproject.places;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlacesController {

	@GetMapping("/gungnamji")
	public String gungnamji() {
		return "places/gungnamji";
	}
	
	@GetMapping("/youngmok")
	public String youngmok() {
		return "places/youngmok";
	}
	
	@GetMapping("/sapgyo")
	public String sapgyo() {
		return "places/sapgyo";
	}
}
