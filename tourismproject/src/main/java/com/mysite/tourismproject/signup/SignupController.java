package com.mysite.tourismproject.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SignupController {
	
	@GetMapping("/signup")
	public String signup() {
		return "signup/signup";
	}
	
	
	@GetMapping("/signin")
	public String signin() {
		return "signup/signin";
	}


	
	
}
