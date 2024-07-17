package com.mysite.naverkakaoexam;



import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
@Data
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid;
	@Column(unique = true)
	private String username; // for SpringSecurity Policy
	private String password; // for SpringSecurity Policy
	private boolean enabled; // for SpringSecurity Policy
	private String role;           // for SpringSecurity Policy
	@Column(unique = true)
	private String cemail;
	private String cphone;
	private String caddr;
	private LocalDateTime cdate;
}
