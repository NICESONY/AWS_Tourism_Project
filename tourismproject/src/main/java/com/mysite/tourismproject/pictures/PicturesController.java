package com.mysite.tourismproject.pictures;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PicturesController {
	
	@GetMapping("/pictures")
	public String pictures() {
		return "pictures/pictures";
	}

}
