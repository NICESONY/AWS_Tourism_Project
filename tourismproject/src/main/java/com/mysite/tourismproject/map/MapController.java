package com.mysite.tourismproject.map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MapController {

	@GetMapping("/map")
	public String map() {
		return "map/map";
	}

}
