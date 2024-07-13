package com.mysite.tourismproject.mbti;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MbtiController {

	@GetMapping("/mbti")
	public String mbti() {
		return "mbti/mbti";
	}

}
