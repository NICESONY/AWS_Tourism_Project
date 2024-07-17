package com.mysite.tourismproject.signup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service 
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, 
    					   String email, 
    					   String password, 
    					   String residence) throws DataIntegrityViolationException {

    	
    	
        if (userRepository.findByUsername(username).isPresent()) {
            throw new DataIntegrityViolationException("이미 존재하는 사용자 ID입니다.");
        }

        
        
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DataIntegrityViolationException("이미 존재하는 이메일입니다.");
        }

        
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setResidence(residence);

        // 주입된 PasswordEncoder를 사용하여 비밀번호 암호화
        user.setPassword(passwordEncoder.encode(password));

        this.userRepository.save(user);
        return user;
        
        
    }
    
}
