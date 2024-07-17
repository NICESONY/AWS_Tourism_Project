package com.mysite.naverkakaoexam;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	
	Optional<Customer> findByusername(String username); // login check

}
