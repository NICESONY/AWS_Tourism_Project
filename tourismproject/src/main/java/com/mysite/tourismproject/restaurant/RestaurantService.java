package com.mysite.tourismproject.restaurant;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mysite.tourismproject.picture.Picture;
import com.mysite.tourismproject.picture.PictureService;

@Service
public class RestaurantService {
	
	 @Autowired
	    private RestaurantRepository restaurantRepository;
	 @Autowired
	    private PictureService pictureService;
	    public void create(Restaurant restaurant) {
	        restaurantRepository.save(restaurant);
	    }
	    
	public List<Restaurant> readlist(){
		return restaurantRepository.findAll();
	}
	public Restaurant findById(Integer id) {
		Optional<Restaurant>or = restaurantRepository.findById(id);
		return or.get();
	}
	public void addRestaurantWithPicture(Restaurant restaurant, MultipartFile addRestaurantfile) throws IOException {
        if (restaurant.getLocationname() == null || restaurant.getLocationname().isEmpty() ||
            restaurant.getLocation() == null || restaurant.getLocation().isEmpty()) {
            throw new IllegalArgumentException("Missing required fields");
        }

        create(restaurant);

        if (!addRestaurantfile.isEmpty()) {
            pictureService.createpicture(restaurant.getId(), addRestaurantfile);
        }
    }
	public Map<Integer, Picture> getFirstPicturesForRestaurants(List<Restaurant> restaurantList) {
        Map<Integer, Picture> firstPictures = new HashMap<>();
        for (Restaurant restaurant : restaurantList) {
            List<Picture> pictures = pictureService.findPicturesByRestaurantId(restaurant.getId());
            if (!pictures.isEmpty()) {
                for (Picture pict : pictures) {
                    if (pict != null) {
                        firstPictures.put(restaurant.getId(), pict);
                        break;
                    }
                }
            } else {
                firstPictures.put(restaurant.getId(), null); // 사진이 없는 경우 null 추가
            }
        }
        return firstPictures;
    }
}