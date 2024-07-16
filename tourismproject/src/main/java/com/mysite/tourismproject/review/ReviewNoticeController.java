package com.mysite.tourismproject.review;

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
public class ReviewNoticeController {
	
	private final ReviewNoticeService ns;
	
	
	
	@Value("${cloud.aws.s3.endpoint}")
	private String downpath ;
	
	
	
	@GetMapping("/review")
	public String showNotices(Model model) {
		model.addAttribute("noticeList", ns.findallnotice());
		return "review/review";
	}
	
	
	
	@GetMapping("/review/add")
	public String addnotice() {
		return "review/addnotice1";
	}
	
	
	
	@PostMapping("/review/create")
	public String createnotice(@ModelAttribute ReviewNotice review,
							@RequestParam("file1") MultipartFile file1,
							@RequestParam("file2") MultipartFile file2,
							@RequestParam("file2") MultipartFile file3) throws IOException
	
	{
		
		ns.createnotice(review, file1, file2, file3);
		
		
		return "redirect:/review";
	}
	
	
	
	
	
	
	@GetMapping("/review/detail/{id}")
	public String shownotice(Model model, 
							@PathVariable("id") Integer id) {
		model.addAttribute("review",ns.getreviewByid(id));
		model.addAttribute("downpath", "https://" + downpath);
		
		return "review/noticedetail1";
	}
	
	
	
	
	@GetMapping("/review/detail/delete/{id}")
	public String deleteNotice(@PathVariable("id") Integer id) {
		ns.deleteNotice(id);
		return "redirect:/review";
	}
	
	
	
	@GetMapping("/update/{id}")
	public String updateNotice(Model model,@PathVariable("id") Integer id) {
		model.addAttribute("review",ns.getreviewByid(id));
		return "review/noticefix1";
	}
	
	
	
	
	@PostMapping("/review/update")
	public String update(@ModelAttribute ReviewNotice review,
			@RequestParam("file1") MultipartFile file1,
			@RequestParam("file2") MultipartFile file2,
			@RequestParam("file3") MultipartFile file3
			)throws IOException
	
{

		ns.update(review, file1, file2, file3);
		return "redirect:/review";
	}
}
