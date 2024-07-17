package com.mysite.tourismproject.pictures;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PicturesRepository1 extends JpaRepository<Pictures, Integer> {
    List<Pictures> findByLocationId(Integer locationId);
}
