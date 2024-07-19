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
	                            @RequestParam("location") String location,
	                            @RequestParam(name = "cphone", required = false) String cphone,
	                            @RequestParam(name = "worktime", required = false) String worktime,
	                            @RequestParam(name = "category", required = false) List<String> category,
	                            @RequestParam(name = "price", required = false) List<String> price,
	                            @RequestParam(name = "productmenu", required = false) List<String> productmenu) {
	    Restaurant restaurant = new Restaurant();
	    restaurant.setLocationname(locationname);
	    restaurant.setLocation(location);
	    if (cphone != null && !cphone.isEmpty()) {
	        restaurant.setCphone(cphone);
	    }
	    if (worktime != null && !worktime.isEmpty()) {
	        restaurant.setWorktime(worktime);
	    }
	    if (category != null && !category.isEmpty()) {
	        restaurant.setCategory(category);
	    }
	    if (productmenu != null && !productmenu.isEmpty()) {
	        restaurant.setProductmenu(productmenu);
	    }
	    if (price != null && !price.isEmpty()) {
	        restaurant.setPrice(price);
	    }
	    restaurantService.locationcreate(restaurant);
	    return "redirect:/restaurant";
	}
	@GetMapping("/detail/{rid}")
    public String getRestaurantPictures(Model model, @PathVariable("rid") Integer rid) {
        Restaurant restaurant = restaurantService.findById(rid);
        List <Restaurant> restaurants = restaurantService.readlist();
        for(Restaurant sbrestaurant :restaurants) {
            if(rid == sbrestaurant.getId()) {
            	model.addAttribute("restaurantId", restaurant.getId());
            }
        }
        List<Picture> pictures = pictureService.findPicturesByRestaurantId(rid);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("pictures", pictures);
        model.addAttribute("downpath", "https://" + downpath);
        return "restaurant/restaurant";
    }
	
	
}
