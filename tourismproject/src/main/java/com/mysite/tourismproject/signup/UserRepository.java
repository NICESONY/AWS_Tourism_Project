package com.mysite.tourismproject.signup;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	 Optional<SiteUser> findByusername(String username);
	 // 아이디 찾아 있으면 가져와라는 의미

}
