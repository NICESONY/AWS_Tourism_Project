package com.mysite.tourismproject.hotel;

import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class HotelController {
	
	@GetMapping("/Hotel")
	public String Hotel() {
		return "hotel/Hotel";
	}
	
	@GetMapping("/list")
	public String list() {
		return "hotel/list";
	}

//    public final Hotel hotels;


//    public HotelController(Hotel hotel) {
//        this.hotels = hotel;
//    }
//
//    // GET 요청을 처리하는 메소드: /hotel 경로로 접근
//    @GetMapping("/Hotel")
//    public String hotelPage() {
//        return "hotel/Hotel"; // "hotel/Hotel.html"을 찾아서 반환 (Thymeleaf 템플릿)
//    }
//
    @PostMapping("/list")
    public String searchHotels(@RequestParam("hotel-name") String hotelName,
                               @RequestParam("checkin-date") LocalDate checkinDate,
                               @RequestParam("checkout-date") LocalDate checkoutDate,
                               @RequestParam("rooms") int rooms,
                               Model model) {

//         여기서는 실제로는 데이터베이스에서 데이터를 가져오지만, 여기서는 임시로 리스트를 만듭니다.
//        List<Hotel> hotels = createSampleHotelList();

        // 결과를 모델에 추가하여 HTML 템플릿에서 사용할 수 있도록 합니다.

//        model.addAttribute("hotels", hotels);
        model.addAttribute("hotelName", hotelName);
        model.addAttribute("checkinDate", checkinDate);
        model.addAttribute("checkoutDate", checkoutDate);
        model.addAttribute("rooms", rooms);

        return "hotel/list"; // 검색 결과를 보여줄 뷰 이름
    }
//
//    // 임시로 호텔 리스트를 생성하는 메소드
//    private List<Hotel> createSampleHotelList() {
//        List<Hotel> hotels = new ArrayList<>();
//        hotels.add(new Hotel(1L, "Hotel A", "Seoul", LocalDate.now(), LocalDate.now().plusDays(1), 2));
//        hotels.add(new Hotel(2L, "Hotel B", "Busan", LocalDate.now(), LocalDate.now().plusDays(1), 1));
//        hotels.add(new Hotel(3L, "Hotel C", "Jeju", LocalDate.now(), LocalDate.now().plusDays(1), 3));
//        return hotels;
//    }
}