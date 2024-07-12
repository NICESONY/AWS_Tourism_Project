package com.mysite.tourismproject.faq;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FAQController {
	
	@GetMapping("/faq")
	public String FAQ() {
		return "faq/faq";
	}


}
