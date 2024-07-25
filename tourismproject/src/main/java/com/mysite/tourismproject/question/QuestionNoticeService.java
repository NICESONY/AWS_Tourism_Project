package com.mysite.tourismproject.question;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.tourismproject.S3Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service 
public class QuestionNoticeService {

    private final QuestionNoticeRepository nr;
    private final S3Service s3Service;

    
    
    private static final Logger logger = LoggerFactory.getLogger(QuestionNoticeService.class);

    
    
    public void createnotice(QuestionNotice question, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        
    	if (file1 != null && !file1.isEmpty()) {
            s3Service.uploadFile(file1, file1.getOriginalFilename());
            question.setImage1(file1.getOriginalFilename());
        }
    	
        if (file2 != null && !file2.isEmpty()) {
            s3Service.uploadFile(file2, file2.getOriginalFilename());
            question.setImage2(file2.getOriginalFilename());
        }
        
        if (file3 != null && !file3.isEmpty()) {
            s3Service.uploadFile(file3, file3.getOriginalFilename());
            question.setImage3(file3.getOriginalFilename());
        }
        
        question.setDate(LocalDateTime.now());
        this.nr.save(question);
    }

    public List<QuestionNotice> findallnotice() {
        return nr.findAll();
    }

    public QuestionNotice getquestionByid(Integer id) {
        Optional<QuestionNotice> op = this.nr.findById(id);
        return op.orElseThrow(() -> new IllegalArgumentException("Invalid question notice ID: " + id));
    }

    public void deleteNotice(Integer id) {
        nr.deleteById(id);
    }

    public void update(QuestionNotice question, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        if (file1 != null && !file1.isEmpty()) { 
            s3Service.uploadFile(file1, file1.getOriginalFilename()); 
            question.setImage1(file1.getOriginalFilename());
        }
        
        if (file2 != null && !file2.isEmpty()) { 
            s3Service.uploadFile(file2, file2.getOriginalFilename()); 
            question.setImage2(file2.getOriginalFilename());
        }
        
        if (file3 != null && !file3.isEmpty()) { 
            s3Service.uploadFile(file3, file3.getOriginalFilename()); 
            question.setImage3(file3.getOriginalFilename());
        }
        nr.save(question);
    }
}
