package com.mysite.tourismproject.pictures;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.tourismproject.S3Service;
import com.mysite.tourismproject.restaurant.Location;
import com.mysite.tourismproject.restaurant.LocationRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PictureService {

    private final PicturesRepository1 pictureRepository;
    private final S3Service s3service;
    private final LocationRepository locationRepository;

    public void createPictures(Integer locationId, MultipartFile file1) throws IOException {
        Pictures picture = new Pictures();
        if (!file1.isEmpty()) {
            s3service.uploadFile(file1, file1.getOriginalFilename());
            picture.setImage1(file1.getOriginalFilename());
        }
        picture.setDate(LocalDateTime.now());

        Location location = locationRepository.findById(locationId).orElseThrow(() -> new RuntimeException("Location not found"));
        picture.setLocation(location);

        this.pictureRepository.save(picture);
    }

    public List<Pictures> findAllPictures() {
        return pictureRepository.findAll();
    }

    public List<Pictures> findPicturesByLocationId(Integer locationId) {
        return pictureRepository.findByLocationId(locationId);
    }

    public Pictures getPictureById(Integer id) {
        Optional<Pictures> op = this.pictureRepository.findById(id);
        return op.orElseThrow(() -> new RuntimeException("Picture not found"));
    }

    public void deletePicture(Integer id) {
        pictureRepository.deleteById(id);
    }

    public void updatePicture(Integer id, MultipartFile file1) throws IOException {
        Pictures picture = getPictureById(id);
        if (!file1.isEmpty()) {
            s3service.uploadFile(file1, file1.getOriginalFilename());
            picture.setImage1(file1.getOriginalFilename());
        }
        picture.setDate(LocalDateTime.now());

        pictureRepository.save(picture);
    }
}
