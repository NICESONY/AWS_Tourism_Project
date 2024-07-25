package com.mysite.tourismproject.question;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.tourismproject.S3Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service
public class QuestionCommentService {
   
   private final QuestionCommentRepository questionCommentRepository;
   private final QuestionNoticeService questionNoticeService;
   
   public void create(String content, Integer id) {
      QuestionComment questionComment = new QuestionComment();
      questionComment.setContent(content);
      questionComment.setDate(LocalDateTime.now());
      
      
      questionComment.setQuestionNotice(questionNoticeService.getquestionByid(id));
      
      questionCommentRepository.save(questionComment);
   }
   
   
   public void delete(Integer id) {
      questionCommentRepository.deleteById(id);
   }
   
   
   public QuestionComment getComment(Integer id) {
      Optional<QuestionComment> op = questionCommentRepository.findById(id);
      return op.get();
   }
   
   
   public void update(QuestionComment questionComment) {
      questionCommentRepository.save(questionComment);
   }
}
