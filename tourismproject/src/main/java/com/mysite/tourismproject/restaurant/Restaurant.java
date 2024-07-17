package com.mysite.tourismproject.restaurant;

//import java.util.List;

//import com.mysite.homework7.yoojinwon.Picture.location.Location;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Restaurant {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	private String description;
	/*@ManyToOne
	List <Location> locationList;*/
}
