package com.mysite.tourismproject.restaurant;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import lombok.RequiredArgsConstructor;
import com.mysite.tourismproject.pictures.PictureService;

@Controller
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final PictureService pictureService;

    @GetMapping("/locations")
    public String showAllLocations(Model model) {
        model.addAttribute("locations", locationService.getAllLocations());
        return "location/list";
    }

    @GetMapping("/locations/{id}")
    public String showLocationDetails(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("location", locationService.getLocationById(id));
        model.addAttribute("pictures", pictureService.findPicturesByLocationId(id));
        return "location/detail";
    }
}
