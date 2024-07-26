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

    private final QuestionNoticeRepository questionNoticeRepository;
    private final S3Service s3Service;

    private static final Logger logger = LoggerFactory.getLogger(QuestionNoticeService.class);

    public void createnotice(QuestionNotice question, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        if (file1 != null && !file1.isEmpty()) {
            String fileName1 = file1.getOriginalFilename();
            s3Service.uploadFile(file1, fileName1);
            question.setImage1(fileName1);
        }

        if (file2 != null && !file2.isEmpty()) {
            String fileName2 = file2.getOriginalFilename();
            s3Service.uploadFile(file2, fileName2);
            question.setImage2(fileName2);
        }

        if (file3 != null && !file3.isEmpty()) {
            String fileName3 = file3.getOriginalFilename();
            s3Service.uploadFile(file3, fileName3);
            question.setImage3(fileName3);
        }

        question.setDate(LocalDateTime.now());
        questionNoticeRepository.save(question);
    }

    public List<QuestionNotice> findallnotice() {
        return questionNoticeRepository.findAll();
    }

    public QuestionNotice getquestionByid(Integer id) {
        Optional<QuestionNotice> op = questionNoticeRepository.findById(id);
        return op.orElseThrow(() -> new IllegalArgumentException("Invalid question notice ID: " + id));
    }

    public void deleteNotice(Integer id) {
        try {
            QuestionNotice question = getquestionByid(id);
            if (question.getImage1() != null) {
                s3Service.deleteImage(question.getImage1());
            }
            if (question.getImage2() != null) {
                s3Service.deleteImage(question.getImage2());
            }
            if (question.getImage3() != null) {
                s3Service.deleteImage(question.getImage3());
            }
            questionNoticeRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting notice with ID {}: {}", id, e.getMessage());
            throw e; // 또는 다른 적절한 예외 처리
        }
    }

    public void update(QuestionNotice question, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        if (file1 != null && !file1.isEmpty()) {
            String fileName1 = file1.getOriginalFilename();
            s3Service.uploadFile(file1, fileName1);
            question.setImage1(fileName1);
        }

        if (file2 != null && !file2.isEmpty()) {
            String fileName2 = file2.getOriginalFilename();
            s3Service.uploadFile(file2, fileName2);
            question.setImage2(fileName2);
        }

        if (file3 != null && !file3.isEmpty()) {
            String fileName3 = file3.getOriginalFilename();
            s3Service.uploadFile(file3, fileName3);
            question.setImage3(fileName3);
        }
        questionNoticeRepository.save(question);
    }

    public void deleteImage1(Integer id) {
        QuestionNotice question = getquestionByid(id);
        if (question.getImage1() != null) {
            s3Service.deleteImage(question.getImage1()); // S3에서 파일 삭제
            question.setImage1(null);
            questionNoticeRepository.save(question);
        }
    }

    public void deleteImage2(Integer id) {
        QuestionNotice question = getquestionByid(id);
        if (question.getImage2() != null) {
            s3Service.deleteImage(question.getImage2()); // S3에서 파일 삭제
            question.setImage2(null);
            questionNoticeRepository.save(question);
        }
    }

    public void deleteImage3(Integer id) {
        QuestionNotice question = getquestionByid(id);
        if (question.getImage3() != null) {
            s3Service.deleteImage(question.getImage3()); // S3에서 파일 삭제
            question.setImage3(null);
            questionNoticeRepository.save(question);
        }
    }
}
