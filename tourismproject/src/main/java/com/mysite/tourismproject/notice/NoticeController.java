package com.mysite.tourismproject.notice;

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
public class NoticeController {
	
	private final NoticeService ns;
	
	
	
	@Value("${cloud.aws.s3.endpoint}")
	private String downpath ;
	
	
	
	@GetMapping("/notice")
	public String showNotices(Model model) {
		model.addAttribute("noticeList", ns.findallnotice());
		return "notice/notice";
	}
	
	
	
	@GetMapping("/notice/add")
	public String addnotice() {
		return "notice/addnotice2";
	}
	
	
	
	@PostMapping("/notice/create")
	public String createnotice(@ModelAttribute Notice notice,
							@RequestParam("file1") MultipartFile file1,
							@RequestParam("file2") MultipartFile file2,
							@RequestParam("file2") MultipartFile file3) throws IOException
	
	{
		
		ns.createnotice(notice, file1, file2, file3);
		
		
		return "redirect:/notice";
	}
	
	
	
	
	
	
	@GetMapping("/notice/detail/{id}")
	public String shownotice(Model model, 
							@PathVariable("id") Integer id) {
		model.addAttribute("notice",ns.getreviewByid(id));
		model.addAttribute("downpath", "https://" + downpath);
		
		return "notice/noticedetail2";
	}
	
	
	
	
	@GetMapping("/notice/detail/delete/{id}")
	public String deleteNotice(@PathVariable("id") Integer id) {
		ns.deleteNotice(id);
		return "redirect:/notice";
	}
	
	
	
	@GetMapping("/update2/{id}")
	public String updateNotice(Model model,@PathVariable("id") Integer id) {
		model.addAttribute("notice",ns.getreviewByid(id));
		return "notice/noticefix2";
	}
	
	
	
	
	@PostMapping("/notice/update2")
	public String update(@ModelAttribute Notice notice,
			@RequestParam("file1") MultipartFile file1,
			@RequestParam("file2") MultipartFile file2,
			@RequestParam("file3") MultipartFile file3
			)throws IOException
	
{

		ns.update(notice, file1, file2, file3);
		return "redirect:/notice";
	}
}
