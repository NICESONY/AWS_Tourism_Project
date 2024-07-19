package com.mysite.tourismproject.signup;



import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Service
public class UserSecurityService implements UserDetailsService {

	@Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SiteUser> tcustomer = userRepository.findByUsername(username);
        if (tcustomer.isEmpty()) {
            throw new UsernameNotFoundException("You need to Sign up first...");
        }
        SiteUser siteuser = tcustomer.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(siteuser.getRole()));
        return new User(siteuser.getUsername(), siteuser.getPassword(), authorities);
    }


	
	public SiteUser authen() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		Optional<SiteUser> oc = userRepository.findByUsername(username);
		return oc.get();
	}
	
	//naver logincheck
		@Autowired
		private HttpServletRequest req;
		public int logincheck(String username) throws UsernameNotFoundException {
			Optional<SiteUser> tcustomer = userRepository.findByUsername(username);
			
			if (tcustomer.isEmpty()) {
				return 1;//db에 없음, 회원 가입으로
			}
			
			SiteUser siteUser = tcustomer.get();
			List<GrantedAuthority> authorities = new ArrayList<>();
			if ("ROLE_USER".equals(siteUser.getRole())) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			} else if ("ROLE_MANAGER".equals(siteUser.getRole())) {
			authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
			} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			//스프링 시큐리티 규격에 맞게 로그인 처리
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(new UsernamePasswordAuthenticationToken(siteUser.getUsername(), siteUser.getPassword(), authorities));
			HttpSession session = req.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,sc);
		
			return 0; //db에 있음, 세션 처리까지
		}
}