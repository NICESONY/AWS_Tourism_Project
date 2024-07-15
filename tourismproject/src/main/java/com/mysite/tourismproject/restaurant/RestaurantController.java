package com.mysite.tourismproject.restaurant;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RestaurantController {
	
	
	@GetMapping("/Restaurant")
	public String Restaurant() {
		return "Restaurant/menu2";
	}
	@GetMapping("/Restaurant/menu2")
	public String Restaurantmenu2() {
		return "Restaurant/Restaurant";
	}
	@GetMapping("/Restaurant/addRestaurant")
	public String addRestaurant() {
		return "Restaurant/addRestaurant";
	}
	/*@PostMapping("/notice/create")
	public String createnotice(@ModelAttribute Notice1 notice,
			@RequestParam("file1") MultipartFile file1) throws IOException {
		
		ns.createnotice(notice, file1);
		
		return "redirect:/";
	}*/
}
