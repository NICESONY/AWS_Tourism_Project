package com.mysite.tourismproject.picture;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequestMapping("/picture")
@RequiredArgsConstructor
@Controller
public class PictureController {
	
	private final PictureService ps;
	
	@Value("${cloud.aws.s3.endpoint}")
	private String downpath;
	
	@GetMapping("")
	public String showNotices(Model model) {
		model.addAttribute("pictureList", ps.findallpictures());
		return "picture/picturelist";
	}
	
	@GetMapping("/add")
	public String addpicture() {
		return "picture/addpicture";
	}
	@GetMapping("/add/{id}")
	public String addRestaurantPicture(@PathVariable("id") Integer id) {
		return "picture/addpicture";
	}
	@PostMapping("/create")
	public String createpicture(@ModelAttribute Picture picture,
			@RequestParam("file111") MultipartFile file111,
			@RequestParam("restaurantId") Integer restaurantId) throws IOException {
		picture.setRestaurantId(restaurantId);
		ps.createpicture(picture, file111);
		
		return "redirect:/restaurant/detail/" + restaurantId;
	}
	
	@GetMapping("/details/{restaurantId}")
	public String showRestaurantPictures(Model model, 
							@PathVariable("restaurantId") Integer restaurantId) {
		List<Picture> pictures = ps.findPicturesByRestaurantId(restaurantId);
		model.addAttribute("pictures", pictures);
		model.addAttribute("downpath","https://"+downpath);
		return "restaurant/restaurant";
	}
}
