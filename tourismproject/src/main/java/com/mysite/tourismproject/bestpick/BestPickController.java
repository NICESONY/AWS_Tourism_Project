package com.mysite.tourismproject.bestpick;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BestPickController {

	@GetMapping("/bestpick")
	public String bestpick() {
		return "bestpick/bestpick";
	}

}
