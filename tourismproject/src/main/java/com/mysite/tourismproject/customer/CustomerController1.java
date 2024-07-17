package com.mysite.tourismproject.customer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController1 {
	
	@GetMapping("/customer1")
	public String customer1() {
		return "customer1/customer1";
	}


}
