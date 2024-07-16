package com.mysite.tourismproject.question;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerControlller {
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	
	 @PostMapping("/create/{id}")
	    public String createAnswer(Model model, @PathVariable("id") Integer id, 
	            @Valid AnswerForm answerForm, BindingResult bindingResult) {
	        Question question = this.questionService.getQuestion(id);
	        if (bindingResult.hasErrors()) {
	            model.addAttribute("question", question);
	            return "question_detail";
	        }
	        this.answerService.craete(question, answerForm.getContent()); //오타 create
	        return String.format("redirect:/question/detail/%s", id);
	    }
}
