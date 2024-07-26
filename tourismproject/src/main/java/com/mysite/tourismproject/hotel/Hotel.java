package com.mysite.tourismproject.hotel;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Hotel {
    @Id
    private Long id;
    private String name;
    private String city;
    private LocalDate checkinDate;
    private LocalDate checkoutDate;
    private int rooms;

    public Hotel(Long id, String name, String city, LocalDate checkinDate, LocalDate checkoutDate, int rooms) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.checkinDate = checkinDate;
        this.checkoutDate = checkoutDate;
        this.rooms = rooms;
    }
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public LocalDate getCheckinDate() {
//		return checkinDate;
//	}
//
//	public void setCheckinDate(LocalDate checkinDate) {
//		this.checkinDate = checkinDate;
//	}
//
//	public LocalDate getCheckoutDate() {
//		return checkoutDate;
//	}
//
//	public void setCheckoutDate(LocalDate checkoutDate) {
//		this.checkoutDate = checkoutDate;
//	}
//
//	public int getRooms() {
//		return rooms;
//	}
//
//	public void setRooms(int rooms) {
//		this.rooms = rooms;
//	}

}
