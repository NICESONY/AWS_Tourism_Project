package com.mysite.tourismproject.notice;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NoticeController {

	@GetMapping("/notice")
	public String notice() {
		
	return "notice/notice";
	}
	
	@GetMapping("/noticecreate")
	public String noticecreate() {
		
	return "notice/noticecreate";
	}
	
}