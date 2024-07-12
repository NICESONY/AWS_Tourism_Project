package com.mysite.tourismproject.enjoy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class EnjoyController {

	@GetMapping("/enjoy")
	public String enjoy() {
		return "enjoy/enjoy";
	}

}
