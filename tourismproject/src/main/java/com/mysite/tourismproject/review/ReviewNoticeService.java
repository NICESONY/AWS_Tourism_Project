package com.mysite.tourismproject.review;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.tourismproject.S3Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor 
@Service 
public class ReviewNoticeService {

    private final ReviewNoticeRepository nr;
    private final S3Service s3Service;

    public void createnotice(ReviewNotice review, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException {
        s3Service.uploadFile(file1, file1.getOriginalFilename());
        s3Service.uploadFile(file2, file2.getOriginalFilename());
        s3Service.uploadFile(file3, file3.getOriginalFilename());

        review.setImage1(file1.getOriginalFilename());
        review.setImage2(file2.getOriginalFilename());
        review.setImage3(file3.getOriginalFilename());
        review.setDate(LocalDateTime.now());
        this.nr.save(review);
    }

    public List<ReviewNotice> findallnotice(){
        return nr.findAll();
    }

    public ReviewNotice getreviewByid(Integer id) {
        Optional<ReviewNotice> op = this.nr.findById(id);
        return op.get();
    }

    public void deleteNotice(Integer id) {
        nr.deleteById(id);
    }

    public void update(ReviewNotice review, MultipartFile file1, MultipartFile file2, MultipartFile file3) throws IOException{
        // 이미지 1만 변화가 있는 경우
        if (!file1.isEmpty()) { 
            s3Service.uploadFile(file1, file1.getOriginalFilename()); 
            review.setImage1(file1.getOriginalFilename());
        }

        //이미지 2만 변화가 있는 경우
        if (!file2.isEmpty()) { 
            s3Service.uploadFile(file2, file2.getOriginalFilename()); 
            review.setImage2(file2.getOriginalFilename());
        }

        //이미지 3만 변화가 있는 경우
        if (!file3.isEmpty()) { 
            s3Service.uploadFile(file3, file3.getOriginalFilename()); 
            review.setImage3(file3.getOriginalFilename());
        }

        // 기타 변화 없는 경우
        nr.save(review);
    }
}
