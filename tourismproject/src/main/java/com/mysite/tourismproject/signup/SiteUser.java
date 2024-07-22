package com.mysite.tourismproject.signup;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String username;
    
    private String password;
    
    @Column(unique = true)
    private String email;
    
    private String phone;
    
    private LocalDateTime date;
    
    private String role; 
    
    
    private String residence;
    
    
    @Column(nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean enabled;
    
}
