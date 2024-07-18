package com.mysite.tourismproject.picture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Integer> {
    List<Picture> findByRestaurant_Id(Integer restaurantId);

}
