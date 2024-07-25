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
     
   private final QuestionCommentService questionCommentService;
   
   @PostMapping("/comment3/create/{id}")
   public String commentCreatequestion(@RequestParam("content") String content, 
                        @PathVariable("id") Integer id) {
	   questionCommentService.create(content ,id);
      
      return "redirect:/question/detail/"+ id;
   }
   
   @GetMapping("/comment3/delete/{nid}/{cid}")
   public String commentDeletequestion(@PathVariable("nid") Integer nid, 
                        @PathVariable("cid") Integer cid) {
      
      questionCommentService.delete(cid);   
      return "redirect:/question/detail/"+ nid;
   }
   
   
   @GetMapping("/comment3/update3/{id}")
   public String commentFixquestion(Model model, @PathVariable("id") Integer id) {
      model.addAttribute("comment3",questionCommentService.getComment(id));
      return "question/commentfix3";
   }
   
   
   @PostMapping("/comment3/update3/{id}")
   public String commentUpdatequestion(@RequestParam("content") String content,@PathVariable("id") Integer id) {
      QuestionComment questionComment = questionCommentService.getComment(id);
      questionComment.setContent(content);
      questionCommentService.update(questionComment);
      return "redirect:/question/detail/" + questionComment.getQuestion().getId();
   }
}
