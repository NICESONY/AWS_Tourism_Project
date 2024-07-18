/*
 * package com.mysite.tourismproject.admin;
 * 
 * 
 * import java.time.LocalDateTime; import java.util.ArrayList; import
 * java.util.List; import java.util.Optional;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.Authentication; import
 * org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.context.SecurityContextHolder; import
 * org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.stereotype.Service;
 * 
 * @Service public class AdminService implements UserDetailsService {
 * 
 * @Autowired private AdminRepository adminRepository;
 * 
 * @Override public UserDetails loadUserByUsername(String username) throws
 * UsernameNotFoundException { Optional<Admin> tcustomer =
 * adminRepository.findByusername(username); if (tcustomer.isEmpty()) { throw
 * new UsernameNotFoundException("You need to Sign up first..."); } Admin admin
 * = tcustomer.get(); List<GrantedAuthority> authorities = new ArrayList<>(); if
 * ("ROLE_USER".equals(admin.getRole())) { authorities.add(new
 * SimpleGrantedAuthority("ROLE_USER")); } else if
 * ("ROLE_MANAGER".equals(admin.getRole())) { authorities.add(new
 * SimpleGrantedAuthority("ROLE_MANAGER")); } else { authorities.add(new
 * SimpleGrantedAuthority("ROLE_ADMIN")); } return new User(admin.getUsername(),
 * admin.getPassword(), authorities); }
 * 
 * 
 * public void create(Admin admin) { admin.setEnabled(true);
 * admin.setRole("ROLE_USER"); // ROLE_ADMIN, ROLE_MANAGER, ROLE_PAID...
 * admin.setCdate(LocalDateTime.now()); BCryptPasswordEncoder passwordEncoder =
 * new BCryptPasswordEncoder();
 * admin.setPassword(passwordEncoder.encode(admin.getPassword()));
 * adminRepository.save(admin); }
 * 
 * 
 * 
 * public List<Admin> readlist() { return adminRepository.findAll(); }
 * 
 * 
 * 
 * public Admin readdetail(Integer cid) { Optional<Admin> oc =
 * adminRepository.findById(cid); return oc.get(); }
 * 
 * public void update(Admin admin) { adminRepository.save(admin);
 * 
 * }
 * 
 * public Admin authen() { Authentication authentication =
 * SecurityContextHolder.getContext().getAuthentication(); UserDetails
 * userDetails = (UserDetails) authentication.getPrincipal(); String username =
 * userDetails.getUsername(); Optional<Admin> oc =
 * adminRepository.findByusername(username); return oc.get(); }
 * 
 * 
 * 
 * }
 */