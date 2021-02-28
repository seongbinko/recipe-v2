package com.seongbindb.recipe.validator;

import com.seongbindb.recipe.form.UserNicknameForm;
import com.seongbindb.recipe.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class UserNicknameFormValidator implements Validator {

    private final UserMapper userMapper;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(UserNicknameForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserNicknameForm userNicknameForm = (UserNicknameForm) o;
        if(userMapper.existByNickName(userNicknameForm.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", "이미 존재하는 닉네임입니다.");
        }
    }
}
