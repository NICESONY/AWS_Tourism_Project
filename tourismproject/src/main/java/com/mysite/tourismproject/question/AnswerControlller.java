package com.mysite.tourismproject.question;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.tourismproject.review.ReviewComment;

import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerControlller {
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, 
	                           @RequestParam("content") String content,
	                           BindingResult bindingResult) {
	    Question question = this.questionService.getQuestion(id);
	    if (content == null || content.trim().isEmpty()) {
	        model.addAttribute("question", question);
	        model.addAttribute("contentError", "Content is required.");
	        return "question/question_detail";
	    }
	    if (bindingResult.hasErrors()) {
	        model.addAttribute("question", question);
	        return "question/question_detail";
	    }
	    this.answerService.create(question, content);
	    return String.format("redirect:/question/detail/%s", id);
	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		questionService.delete(id);	
		return "redirect:/question/detail/"+ id;
	}
	
	
	@GetMapping("/update/{id}")
	public String update(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("answer", answerService.getAnswer(id));
		return "question/answer";
	}
	
	
	
	@PostMapping("/update/{id}")
	public String update(@RequestParam("content") String s,
			             @PathVariable("id") Integer id) {
		Answer a = answerService.getAnswer(id);
		a.setContent(s);
		answerService.update(a);
		return "redirect:/question/detail/" + a.getQuestion().getId();
	}
	
	
}
