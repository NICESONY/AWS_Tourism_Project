package com.mysite.tourismproject.signup;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Integer> {
    Optional<SiteUser> findByUsername(String username); // 사용자 이름으로 사용자 찾기
    Optional<SiteUser> findByEmail(String email); // 이메일로 사용자 찾기
}
