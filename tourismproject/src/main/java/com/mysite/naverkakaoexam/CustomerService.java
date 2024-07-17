package com.mysite.naverkakaoexam;

import java.time.LocalDateTime;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
@Service
public class CustomerService implements UserDetailsService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Customer> tcustomer = customerRepository.findByusername(username);
		if (tcustomer.isEmpty()) {
			throw new UsernameNotFoundException("You need to Sign up first...");
		}
		Customer customer = tcustomer.get();
		List<GrantedAuthority> authorities = new ArrayList<>();
		if ("ROLE_USER".equals(customer.getRole())) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		} else if ("ROLE_MANAGER".equals(customer.getRole())) {
			authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
		} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		
		return new User(customer.getUsername(), customer.getPassword(), authorities);
	}
	public void create(Customer customer) {
		customer.setEnabled(true);
		customer.setRole("ROLE_USER"); // ROLE_ADMIN, ROLE_MANAGER, ROLE_PAID...
		customer.setCdate(LocalDateTime.now());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
		customerRepository.save(customer);
	}
	public List<Customer> readlist() {
		return customerRepository.findAll();
	}
	
	
	// 현재 접속중인 사용자 정보를 얻어내는 것이다.
	public Customer authen() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String username = userDetails.getUsername();
		Optional<Customer> oc = customerRepository.findByusername(username);
		return oc.get();
	}
	
	
	//naver logincheck
	@Autowired
	private HttpServletRequest req;
	
	
	
	public int logincheck(String username) throws UsernameNotFoundException {
			Optional<Customer> tcustomer = customerRepository.findByusername(username);
			
			if (tcustomer.isEmpty()) {
				return 1;//db에 없음, 회원 가입으로
			}
			
			// 위에서 확인한 바와 같이 DB에 존재하므로 정상적인 로그인 과정을 진행한다.
			// 이때 중요한 점은 스프링 시큐리티의 규격에 맞게 로그인 처리를 수행해야한다.
			// 디비에서 객체를 뽑아온것이다. TCUSTOMER
			Customer customer = tcustomer.get();
			
			
			// 권한 role 처리 주의할 점은 시큐리티 규격에 맞게 처리해야한다.
			List<GrantedAuthority> authorities = new ArrayList<>();
			
			//
			if ("ROLE_USER".equals(customer.getRole())) {
			authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
			} else if ("ROLE_MANAGER".equals(customer.getRole())) {
			authorities.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
			} else {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			
			
			//스프링 시큐리티 규격에 맞게 로그인 처리
			// 세션을 심어주는 단계이다.
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(new UsernamePasswordAuthenticationToken(customer.getUsername(), customer.getPassword(), authorities));
			HttpSession session = req.getSession(true);
			session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,sc);
		
			return 0; //db에 있음, 세션 처리까지
		}
	
	
	
	}
