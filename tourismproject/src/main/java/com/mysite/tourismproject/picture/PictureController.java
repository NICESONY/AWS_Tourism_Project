package com.mysite.tourismproject.picture;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PictureController {
	
	private final PictureService ps;
	
	@Value("${cloud.aws.s3.endpoint}")
	private String downpath;
	
	@GetMapping("/showpictures")
	public String showNotices(Model model) {
		model.addAttribute("pictureList", ps.findallpictures());
		return "pictures/picturelist";
	}
	
	@GetMapping("/picture/add")
	public String addpicture() {
		return "pictures/addpicture";
	}
	
	@PostMapping("/picture/create")
	public String createpicture(@ModelAttribute Picture picture,
			@RequestParam("file1") MultipartFile file1) throws IOException {
		
		ps.createpicture(picture, file1);
		
		return "redirect:/showpictures";
	}
	@PostMapping("/restaurant/create")
	public String createpicture1(@ModelAttribute Picture picture,
			@RequestParam("file1") MultipartFile file1) throws IOException {
		
		ps.createpicture(picture, file1);
		
		return "redirect:/showpictures";
	}
	
	@GetMapping("/picture/detail/{id}")
	public String shownotice(Model model, 
							@PathVariable("id") Integer id) {
		model.addAttribute("picture",ps.getpictureByid(id));
		model.addAttribute("downpath","https://"+downpath);
		return "yoojinwon/picturedetail1";
	}
}
