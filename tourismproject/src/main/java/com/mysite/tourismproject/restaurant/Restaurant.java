// Restaurant.java
package com.mysite.tourismproject.restaurant;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String locationname;
    private String location;
    private String cphone;
    private String worktime;

    @ElementCollection
    private List<String> category = new ArrayList<>();

    @ElementCollection
    private List<String> price = new ArrayList<>();

    @ElementCollection
    private List<String> productmenu = new ArrayList<>();
 }