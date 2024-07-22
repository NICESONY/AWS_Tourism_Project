package com.mysite.tourismproject.picture;

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
public class PictureService {

	private final PictureRepository pictureRepository;
	private final S3Service s3service;
	
	public void createpicture(Picture picture, MultipartFile file1) throws IOException {
		if(!file1.isEmpty()) {
			s3service.uploadFile(file1, file1.getOriginalFilename());
			picture.setImage1(file1.getOriginalFilename());
		}
		picture.setDate(LocalDateTime.now());
		this.pictureRepository.save(picture);
	}
	public List<Picture> findallpictures(){
		return pictureRepository.findAll();
	}
	public Picture getpictureByid(Integer id) {
		Optional<Picture> op = this.pictureRepository.findById(id);
		return op.get();
	}
	public List<Picture> findPicturesByRestaurantId(Integer restaurantId) {
		
		return pictureRepository.findByRestaurant_Id(restaurantId);
	}
	/*public List<Picture> findPicturesByLocation(String location) {
        return pictureRepository.findByLocation(location);
    }*/
}
