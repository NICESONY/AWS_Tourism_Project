package com.mysite.tourismproject.bestpick;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BestPickController {

	@GetMapping("/bestpick")
	public String bestpick() {
		return "bestpick/bestpick";
	}

	@GetMapping("/ban")
	public String ban() {
		return "bestpick/ban";
	}
	
	@GetMapping("/kkot")
	public String kkot() {
		return "bestpick/kkot";
	}
	@GetMapping("/gung")
	public String gung() {
		return "bestpick/gung";
	}
	@GetMapping("/an")
	public String an() {
		return "bestpick/an";
	}
	@GetMapping("/ye")
	public String ye() {
		return "bestpick/ye";
	}
	
	@GetMapping("/jong")
	public String jong() {
		return "bestpick/jong";
	}
	@GetMapping("/gan")
	public String gan() {
		return "bestpick/gan";
	}
	@GetMapping("/eun")
	public String eun() {
		return "bestpick/eun";
	}
	

}
