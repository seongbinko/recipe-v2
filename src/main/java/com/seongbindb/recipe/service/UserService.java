package com.seongbindb.recipe.service;

import com.seongbindb.recipe.form.SignUpForm;
import com.seongbindb.recipe.form.UserProfileForm;
import com.seongbindb.recipe.vo.User;

public interface UserService {
    User processNewUser(SignUpForm signUpForm);

    User finByEmail(String email);

    User findByNickname(String nickname);

    int CountAllUser();

    void updateEmailVerified(User user);

    void login(User user);

    void sendSignUpConfirmEmail(User user);

    void completedSignUp(User user);

    void updateUserProfile(User user, UserProfileForm userProfileForm);

    void updatePassword(User user, String newPassword);

    void updateUserNickname(User user, String nickname);

    void sendLoginLink(User user);
}
