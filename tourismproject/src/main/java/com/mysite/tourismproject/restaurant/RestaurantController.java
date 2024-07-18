package com.mysite.tourismproject.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.tourismproject.picture.Picture;
import com.mysite.tourismproject.picture.PictureService;

@RequestMapping("/restaurant")
@Controller
public class RestaurantController {
	@Autowired
	private RestaurantService restaurantService;
	@Autowired
	private PictureService pictureService;
	@Value("${cloud.aws.s3.endpoint}")
	private String downpath;
	@GetMapping("")
	public String Restaurant(Model model) {
		List<Restaurant>restaurants = restaurantService.readlist();
        model.addAttribute("restaurants", restaurants);
		return "restaurant/menu2";
	}
	@GetMapping("/menu2")
	public String Restaurantmenu2() {
		return "restaurant/restaurant";
	}
	@GetMapping("/addrestaurant")
	public String addRestaurant() {
		return "restaurant/addrestaurant";
	}
	@PostMapping("/addrestaurant")
	public String addRestaurant(@RequestParam("locationname") String locationname,
            @RequestParam("description") String description) {
		Restaurant restaurant = new Restaurant();
		restaurant.setLocationname(locationname);
		restaurant.setDescription(description);
		restaurantService.locationcreate(restaurant);
		return "redirect:/restaurant/addrestaurant";
	}
	@GetMapping("/detail/{id}")
    public String getRestaurantPictures(Model model, @PathVariable("id") Integer id
    		) {
		List<Picture> pictures = pictureService.findPicturesByRestaurantId(id);
		//model.addAttribute("restaurant", restaurantService.findById(id));
        model.addAttribute("pictures", pictures);
        //model.addAttribute("restaurantId", id);
		model.addAttribute("downpath","https://"+downpath);

        return "restaurant/restaurant";
    }
}
