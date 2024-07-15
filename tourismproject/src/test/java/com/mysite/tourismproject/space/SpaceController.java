package com.mysite.tourismproject.space;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SpaceController {

	@GetMapping("/space")
	public String space() {
		return "space/space";
	}

}
