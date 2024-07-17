package com.mysite.naverkakaoexam;




import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

@Bean
SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
http
.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
.formLogin((formLogin) -> formLogin
.loginPage("/signin")
.defaultSuccessUrl("/")
)
.logout((logout) -> logout
.logoutRequestMatcher(new AntPathRequestMatcher("/signout"))
.logoutSuccessUrl("/")
.invalidateHttpSession(true))
;
return http.build();
}
@Bean
PasswordEncoder passwordEncoder() {
return new BCryptPasswordEncoder();
}
@Bean
AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
return authenticationConfiguration.getAuthenticationManager();

}
}