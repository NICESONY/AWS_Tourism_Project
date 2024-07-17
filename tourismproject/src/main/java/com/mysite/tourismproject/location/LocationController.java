package com.mysite.tourismproject.location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/location")
@Controller
public class LocationController {
	@Autowired
	private LocationService locationService;
	@GetMapping("/main")
	public String main() {
		return "location/main";
	}
	@GetMapping("/locationcreate")
	public String locationcreate() {
		return "location/locationcreate";
	}
	@PostMapping("/locationcreate")
	public String locationcreate(@ModelAttribute Location location) {
		
		locationService.locationcreate(location);
		return "location/locationcreate";
	}
}
