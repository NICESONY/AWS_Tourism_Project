package com.mysite.tourismproject.question;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.tourismproject.review.ReviewComment;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerService {
	
	private final AnswerRepository answerRepository;
	
	public void create(Question question, String content) {
		Answer answer = new Answer();
		answer.setContent(content);
		answer.setCreateDate(LocalDateTime.now());
		answer.setQuestion(question);
		this.answerRepository.save(answer);
	}
	
	
	public void delete(Integer id) {
		answerRepository.deleteById(id);
	}
	
	public Answer getAnswer(Integer id) {
		Optional<Answer> op = answerRepository.findById(id);
		return op.get();
	}
	
	public void update(Answer a) {
		answerRepository.save(a);
	}

}
