package com.mysite.tourismproject.restaurant;

import java.util.List;

import com.mysite.tourismproject.pictures.Pictures;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Location {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private String name;
	    private String description;

	    @OneToMany(mappedBy = "location")
	    private List<Pictures> pictures;
}
