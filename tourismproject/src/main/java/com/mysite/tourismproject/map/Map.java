package com.mysite.tourismproject.map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Map {
	
	
	 @Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY) 
	 private Integer id; 
	 
	 
	 
	 
	 private String title;
	 
	 
	    
	 // 지소 사용하려고 한것
	 private String addr;
	    

}
