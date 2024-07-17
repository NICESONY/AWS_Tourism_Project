package com.mysite.naverkakaoexam;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NaverController {

	@GetMapping("/naver")
	public String naver() {
		return "NaverLogin";
	}
	
	@GetMapping("/naverlogin")
	public String naverlogin() {
		return "callback";
	}
	
	@Autowired
	CustomerService customerService;
	
	@GetMapping("/logincheck")
	public String logincheck(@RequestParam("email") String email) {
		
		if (1 == customerService.logincheck(email)) {
			return "redirect:/signup"; // 로그인 실패 처리 , 회원 가입 페이지로 이동
		}else {
			return "redirect:/"; // 로그인 성공 처리 , 추후 변경
		}
	}
}