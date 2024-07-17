package com.mysite.naverkakaoexam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class CustomerController {
	
		@Autowired
		private CustomerService customerService;
		

		
		/*
		 * @GetMapping("/") public String signup() { return "signup"; }
		 */
		
		/*
		 * @PostMapping("/signup") public String signup(Customer customer) {
		 * 
		 * customerService.create(customer);
		 * 
		 * return "signup"; }
		 */
		
		@GetMapping("/signin")
		public String signin() {
			return "signin";
		}
		
	}
