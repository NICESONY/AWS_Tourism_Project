package com.mysite.tourismproject.signup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NaverController {
	
	@Autowired
	private UserSecurityService userSecurityService;
	

	@GetMapping("/naver")
	public String naver() {
		return "signup/naverlogin";
	}
	
	@GetMapping("/naverlogin")
	public String naverlogin() {
		return "signup/callback";
	}
	
	@GetMapping("/logincheck")
	public String logincheck(@RequestParam("email") String email) {
		
		if (1 == userSecurityService.logincheck(email)) {
			return "signup/signin"; // 로그인 실패 처리 , 추후 변경
		}else {
			return "index"; // 로그인 성공 처리 , 추후 변경
		}
	}
}
