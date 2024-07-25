package com.mysite.tourismproject.review;

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
public class ReviewNoticeService {

    private final ReviewNoticeRepository nr;
    private final S3Service s3Service;
    
    private static final Logger logger = LoggerFactory.getLogger(ReviewNoticeService.class);

    public void createnotice(ReviewNotice review, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        try {
            if (file1 != null && !file1.isEmpty()) {
                s3Service.uploadFile(file1, file1.getOriginalFilename());
                review.setImage1(file1.getOriginalFilename());
            }
            
            if (file2 != null && !file2.isEmpty()) {
                s3Service.uploadFile(file2, file2.getOriginalFilename());
                review.setImage2(file2.getOriginalFilename());
            }
            
            if (file3 != null && !file3.isEmpty()) {
                s3Service.uploadFile(file3, file3.getOriginalFilename());
                review.setImage3(file3.getOriginalFilename());
            }
            
            review.setDate(LocalDateTime.now());
            this.nr.save(review);
            logger.info("Review notice created: " + review.getId());
        } catch (Exception e) {
            logger.error("Error creating review notice", e);
            throw new IOException("Error creating review notice", e);
        }
    }

    public List<ReviewNotice> findallnotice() {
        return nr.findAll();
    }

    public ReviewNotice getreviewByid(Integer id) {
        Optional<ReviewNotice> op = this.nr.findById(id);
        if (op.isPresent()) {
            return op.get();
        } else {
            logger.error("Review notice not found: " + id);
            throw new IllegalArgumentException("Invalid review notice ID: " + id);
        }
    }

    public void deleteNotice(Integer id) {
        try {
            nr.deleteById(id);
            logger.info("Review notice deleted: " + id);
        } catch (Exception e) {
            logger.error("Error deleting review notice", e);
            throw new IllegalArgumentException("Error deleting review notice with ID: " + id, e);
        }
    }

    public void update(ReviewNotice review, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        try {
            if (file1 != null && !file1.isEmpty()) {
                s3Service.uploadFile(file1, file1.getOriginalFilename());
                review.setImage1(file1.getOriginalFilename());
            }
            
            if (file2 != null && !file2.isEmpty()) {
                s3Service.uploadFile(file2, file2.getOriginalFilename());
                review.setImage2(file2.getOriginalFilename());
            }
            
            if (file3 != null && !file3.isEmpty()) {
                s3Service.uploadFile(file3, file3.getOriginalFilename());
                review.setImage3(file3.getOriginalFilename());
            }
            
            nr.save(review);
            logger.info("Review notice updated: " + review.getId());
        } catch (Exception e) {
            logger.error("Error updating review notice", e);
            throw new IOException("Error updating review notice", e);
        }
    }

    public List<ReviewNotice> findByTitle(String kw) {
        return nr.findByTitleLike("%" + kw + "%");
    }
}
