package com.mysite.tourismproject.signup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserCreateForm {
	
	// 회원가입을 위한 테이블 생성
	
	// 검증용
    @Size(min = 3, max = 25)
    @NotEmpty(message = "사용자ID는 필수항목입니다.")
    private String username;

    
    
    @NotEmpty(message = "비밀번호는 필수항목입니다.")
    private String password1;

    
    
    @NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
    private String password2;

    
    
    @NotEmpty(message = "이메일은 필수항목입니다.")
    @Email
    private String email;
}