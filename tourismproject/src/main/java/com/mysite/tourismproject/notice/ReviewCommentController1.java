package com.mysite.tourismproject.notice;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class ReviewCommentController1 {
	
	private final ReviewCommentService1 cs;
		
	
	
	@PostMapping("/comment2/create/{id}")
	public String commentCreatenotice(@RequestParam("content") String content, 
								@PathVariable("id") Integer id) {
		cs.create(content ,id);
		
		return "redirect:/notice/detail/"+ id;
	}
	
	@GetMapping("/comment2/delete/{nid}/{cid}")
	public String commentDeletenotice(@PathVariable("nid") Integer nid, 
								@PathVariable("cid") Integer cid) {
		
		cs.delete(cid);	
		return "redirect:/notice/detail/"+ nid;
	}
	
	
	@GetMapping("/comment2/update2/{id}")
	public String commentFixnotice(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("comment2",cs.getComment(id));
		return "notice/commentfix2";
	}
	
	
	
	@PostMapping("/comment2/update2/{id}")
	public String commentUpdatenotice(@RequestParam("content") String s,@PathVariable("id") Integer id) {
		ReviewComment1 c = cs.getComment(id);
		c.setContent(s);
		cs.update(c);
		return "redirect:/notice/detail/" + c.getNotice().getId();
	}
}
