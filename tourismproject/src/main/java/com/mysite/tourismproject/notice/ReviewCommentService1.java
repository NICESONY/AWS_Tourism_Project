package com.mysite.tourismproject.notice;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewCommentService1 {
	
	private final ReviewCommentRepository1 cr;
	private final NoticeService ns;
	
	public void create(String content, Integer id) {
		ReviewComment1 comm = new ReviewComment1();
		comm.setContent(content);
		comm.setDate(LocalDateTime.now());
		
		
		comm.setNotice(ns.getreviewByid(id));
		
		cr.save(comm);
	}
	
	
	public void delete(Integer id) {
		cr.deleteById(id);
	}
	
	
	public ReviewComment1 getComment(Integer id) {
		Optional<ReviewComment1> op = cr.findById(id);
		return op.get();
	}
	
	
	public void update(ReviewComment1 c) {
		cr.save(c);
	}
}
