package com.mysite.tourismproject.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	@Autowired
    private UserRepository userRepository;
	
	private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
		/*
		 * BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		 * user.setPassword(passwordEncoder.encode(password));
		 */
        // 주석처리한 곳 대체 되었다.
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }
}



