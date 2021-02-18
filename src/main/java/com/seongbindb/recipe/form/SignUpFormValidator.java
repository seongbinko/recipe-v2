package com.seongbindb.recipe.form;

import com.seongbindb.recipe.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpFormValidator implements Validator {

    private final UserMapper userMapper;
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SignUpForm signUpForm = (SignUpForm) o;
        if(userMapper.existByEmail(signUpForm.getEmail())) {
            errors.rejectValue("email" , "invalid.email", new Object[]{signUpForm.getEmail()}, "이미 존재하는 이메일입니다.");
        }
        if(userMapper.existByNickName(signUpForm.getNickname())) {
            errors.rejectValue("nickname" , "invalid.nickname", new Object[]{signUpForm.getNickname()}, "이미 존재하는 닉네임입니다.");
        }
    }
}
