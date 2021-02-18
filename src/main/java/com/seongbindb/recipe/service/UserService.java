package com.seongbindb.recipe.service;

import com.seongbindb.recipe.form.SignUpForm;
import com.seongbindb.recipe.vo.User;

public interface UserService {
    void processNewUser(SignUpForm signUpForm);
    User finByEmail(String email);
    int CountAllUser();

    void updateEmailVerified(User user);
}
