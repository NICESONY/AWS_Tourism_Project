package com.mysite.tourismproject.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class QuestionController {
	
	private final QuestionRepository questionRepository;
	
	private final QuestionService questionService;
	
	private final AnswerService answerService;

    @GetMapping("/question_list")
    public String list(Model model) {
        List<Question> questionList = this.questionRepository.findAll();
        model.addAttribute("questionList", questionList);
        return "question/question_list";
    }

	@GetMapping("/question_form")
	public String questionForm() {
		return "question/question_form";
	}

	@GetMapping("/question/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question/question_detail";
    }
	
	@PostMapping("/question/create")
	public String questionCreate(@RequestParam("subject") String subject,
	                             @RequestParam("content") String content,
	                             Model model, BindingResult bindingResult) {
	    if (subject == null || subject.trim().isEmpty() || content == null || content.trim().isEmpty()) {
	        model.addAttribute("subject", subject);
	        model.addAttribute("content", content);
	        model.addAttribute("errorMessage", "Subject and content are required.");
	        return "question/question_form";
	    }
	    this.questionService.create(subject, content);
	    return "redirect:/question_list";
	}
	
	@GetMapping("/question/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
	    questionService.delete(id);    
	    return "redirect:/question_list";
	}
}
