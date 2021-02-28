package com.seongbindb.recipe.validator;

import com.seongbindb.recipe.form.UserPasswordForm;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserPasswordFormValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserPasswordForm.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserPasswordForm passwordForm = (UserPasswordForm) target;
        if(!passwordForm.getNewPassword().equals(passwordForm.getNewPasswordConfirm())) {
            errors.rejectValue("newPassword", "wrong.value","입력한 새 패스워드가 서로 일치하지 않습니다.");
        }
    }

}
