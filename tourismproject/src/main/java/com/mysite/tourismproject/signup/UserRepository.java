package com.mysite.tourismproject.signup;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {

	Optional<SiteUser> findByusername(String username);
}

