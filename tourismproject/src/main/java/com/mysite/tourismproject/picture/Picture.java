package com.mysite.tourismproject.picture;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Picture {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id; 

	private String image1;
        
    private LocalDateTime date; 
}