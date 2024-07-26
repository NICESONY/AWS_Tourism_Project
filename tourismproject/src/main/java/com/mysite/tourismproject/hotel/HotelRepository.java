package com.mysite.tourismproject.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class HotelRepository {

    private List<Hotel> hotels = new ArrayList<>();

    public HotelRepository() {
        // 예시 호텔 데이터 초기화
        hotels.add(new Hotel(1L, "Hotel A", "City A", LocalDate.parse("2024-07-25"), LocalDate.parse("2024-07-28"), 2));
        hotels.add(new Hotel(2L, "Hotel B", "City B", LocalDate.parse("2024-07-26"), LocalDate.parse("2024-07-29"), 4));
        hotels.add(new Hotel(2L, "Hotel c", "City c", LocalDate.parse("2024-07-26"), LocalDate.parse("2024-07-29"), 1));
        // 필요한 만큼 추가
    }

    public List<Hotel> findAll() {
        return hotels;
    }
}
