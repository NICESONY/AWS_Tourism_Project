package com.mysite.tourismproject.review;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewNoticeRepository extends JpaRepository<ReviewNotice, Integer> {
	
	List<ReviewNotice> findByTitleLike(String kw);
	 
}
