package com.mysite.tourismproject.question;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionCommentService {
	
	private final QuestionCommentRepository cr;
	private final QuestionNoticeService ns;
	
	public void create(String content, Integer id) {
		QuestionComment comm = new QuestionComment();
		comm.setContent(content);
		comm.setDate(LocalDateTime.now());
		
		
		comm.setQuestion(ns.getquestionByid(id));
		
		cr.save(comm);
	}
	
	
	public void delete(Integer id) {
		cr.deleteById(id);
	}
	
	
	public QuestionComment getComment(Integer id) {
		Optional<QuestionComment> op = cr.findById(id);
		return op.get();
	}
	
	
	public void update(QuestionComment c) {
		cr.save(c);
	}
}
