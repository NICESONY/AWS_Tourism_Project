package com.mysite.tourismproject.signup;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signupForm(UserCreateForm userCreateForm) {
        return "signup/signup"; // 템플릿 경로와 일치해야 함
    }

    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup/signup"; // 템플릿 경로와 일치해야 함
        }

        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
            return "signup/signup"; // 템플릿 경로와 일치해야 함
        }

        try {
            userService.create(userCreateForm.getUsername(), 
            				   userCreateForm.getEmail(), 
            				   userCreateForm.getPassword1(), 
            				   userCreateForm.getResidence());
            				   
            
        } catch(DataIntegrityViolationException e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup/signup"; // 템플릿 경로와 일치해야 함
        } catch(Exception e) {
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup/signup"; // 템플릿 경로와 일치해야 함
        }

        return "redirect:/signin";
    }

    @GetMapping("/signin")
    public String signin() {
        return "signup/signin"; // 여기도 템플릿 경로와 일치해야 함
    }
      
	
	@GetMapping("/admin/main")
	public String admin(Model model ) {
		model.addAttribute("admins", userService.readlist());
		return "signup/main";
	}
	
	@GetMapping("/readdetail/{id}")
	public String detail(Model model,@PathVariable ("id") Integer id) {
		
		model.addAttribute("admin", userService.readdetail(id));
		
		return "signup/readdetail";
	}

	
	@PostMapping("/admin/update")
	public String update(@ModelAttribute SiteUser siteuser) {
		userService.update(siteuser);
		return "redirect:/readdetail/" + siteuser.getId();
	}
	

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null) {
            model.addAttribute("username", userDetails.getUsername());
        }
        return "index"; // 메인 템플릿 경로
    }
}
