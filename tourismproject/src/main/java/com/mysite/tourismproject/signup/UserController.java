package com.mysite.tourismproject.signup;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    
    
    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "signup";
    }

    
    // 건너오면 여기서 검증을 한다. 만약 에러가 있으면 에러를 발생 시킨다.
    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        
        // 다시 검증 : 패스워드가 일치하는지를 검증한다.
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", 
                    "2개의 패스워드가 일치하지 않습니다.");// 에러메시지를 갗이 보내고 표시해준다.
            return "signup";
        }

        
        // 이상이 없으면 진짜 DB에 넣게 된다.
        userService.create(userCreateForm.getUsername(), 
                userCreateForm.getEmail(), userCreateForm.getPassword1());

        
        try {
            userService.create(userCreateForm.getUsername(), 
                    userCreateForm.getEmail(), userCreateForm.getPassword1());
        }catch(DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "signup";
        }catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "signup";
        }
        
        
        return "redirect:/signup/signup";
    }
    
    @GetMapping("/signin")
    public String signin() {
        return "signin";
    }

    
    
    
}