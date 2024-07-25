package com.mysite.tourismproject.question;

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
public class QuestionNoticeController {
   
   private final QuestionNoticeService questionNoticeService;
   
   @Value("${cloud.aws.s3.endpoint}")
   private String downpath ;
   
   @GetMapping("/question")
   public String showNotices(Model model) {
      model.addAttribute("noticeList", questionNoticeService.findallnotice());
      return "question/question";
   }
    
   @GetMapping("/question/add")
   public String addnotice() {
      return "question/addnotice3";
   }
     
   @PostMapping("/question/create")
   public String createnotice(@ModelAttribute QuestionNotice question,
                     @RequestParam("file1") MultipartFile file1,
                     @RequestParam("file2") MultipartFile file2,
                     @RequestParam("file2") MultipartFile file3) throws IOException
   
   {
      questionNoticeService.createnotice(question, file1, file2, file3);     
      return "redirect:/question";
   }
   
   @GetMapping("/question/detail/{id}")
   public String shownotice(Model model, 
                     @PathVariable("id") Integer id) {
      model.addAttribute("question",questionNoticeService.getquestionByid(id));
      model.addAttribute("downpath", "https://" + downpath);
      return "question/noticedetail3";
   }

   @GetMapping("/question/detail/delete/{id}")
   public String deleteNotice(@PathVariable("id") Integer id) {
      questionNoticeService.deleteNotice(id);
      return "redirect:/question";
   }
 
   @GetMapping("/update3/{id}")
   public String updateNotice(Model model,@PathVariable("id") Integer id) {
      model.addAttribute("question",questionNoticeService.getquestionByid(id));
      return "question/noticefix3";
   }
 
   @PostMapping("/question/update3")
   public String update(@ModelAttribute QuestionNotice question,
         @RequestParam("file1") MultipartFile file1,
         @RequestParam("file2") MultipartFile file2,
         @RequestParam("file3") MultipartFile file3
         )throws IOException
   
{
      questionNoticeService.update(question, file1, file2, file3);
      return "redirect:/question";
   }
   
   @GetMapping("/question/deleteImage1/{id}")
   public String deleteImage1(@PathVariable("id") Integer id) {
       questionNoticeService.deleteImage1(id);
       return "redirect:/update3/" + id;
   }

   @GetMapping("/question/deleteImage2/{id}")
   public String deleteImage2(@PathVariable("id") Integer id) {
       questionNoticeService.deleteImage2(id);
       return "redirect:/update3/" + id;
   }

   @GetMapping("/question/deleteImage3/{id}")
   public String deleteImage3(@PathVariable("id") Integer id) {
       questionNoticeService.deleteImage3(id);
       return "redirect:/update3/" + id;
   }
   
 
}
