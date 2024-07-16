package com.mysite.tourismproject.review;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReviewCommentService {
	
	private final ReviewCommentRepository cr;
	private final ReviewNoticeService ns;
	
	public void create(String content, Integer id) {
		ReviewComment comm = new ReviewComment();
		comm.setContent(content);
		comm.setDate(LocalDateTime.now());
		
		
		comm.setNotice(ns.getnoticeByid(id));
		
		cr.save(comm);
	}
	
	
	public void delete(Integer id) {
		cr.deleteById(id);
	}
	
	
	public ReviewComment getComment(Integer id) {
		Optional<ReviewComment> op = cr.findById(id);
		return op.get();
	}
	
	
	public void update(ReviewComment c) {
		cr.save(c);
	}
}
