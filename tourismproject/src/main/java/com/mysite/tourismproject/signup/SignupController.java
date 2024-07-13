package com.mysite.tourismproject.signup;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SignupController {
	
	@GetMapping("/signup")
	public String signup() {
		return "signup/signup";
	}
	
<<<<<<< HEAD
=======
	
>>>>>>> 13d8f1745f1fd35f575683990487d1a742ac04f1
	@GetMapping("/signin")
	public String signin() {
		return "signup/signin";
	}
<<<<<<< HEAD
=======

>>>>>>> 13d8f1745f1fd35f575683990487d1a742ac04f1

	
	
}
