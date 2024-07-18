package com.mysite.tourismproject.picture;

import java.time.LocalDateTime;

import com.mysite.tourismproject.restaurant.Restaurant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    private Restaurant restaurant;
    
    public void setRestaurantId(Integer restaurantId) {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(restaurantId);
		this.restaurant = restaurant;
	}
}