package com.mysite.tourismproject.question;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionCommentController {
	
	private final QuestionCommentService cs;
		
	
	
	@PostMapping("/comment3/create/{id}")
	public String commentCreatequestion(@RequestParam("content") String content, 
								@PathVariable("id") Integer id) {
		cs.create(content ,id);
		
		return "redirect:/question/detail/"+ id;
	}
	
	@GetMapping("/comment3/delete/{nid}/{cid}")
	public String commentDeletequestion(@PathVariable("nid") Integer nid, 
								@PathVariable("cid") Integer cid) {
		
		cs.delete(cid);	
		return "redirect:/question/detail/"+ nid;
	}
	
	
	@GetMapping("/comment3/update3/{id}")
	public String commentFixquestion(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("comment3",cs.getComment(id));
		return "question/commentfix3";
	}
	
	
	
	@PostMapping("/comment3/update3/{id}")
	public String commentUpdatequestion(@RequestParam("content") String s,@PathVariable("id") Integer id) {
		QuestionComment c = cs.getComment(id);
		c.setContent(s);
		cs.update(c);
		return "redirect:/question/detail/" + c.getQuestion().getId();
	}
}
