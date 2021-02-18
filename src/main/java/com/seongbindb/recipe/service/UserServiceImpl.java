package com.seongbindb.recipe.service;


import com.seongbindb.recipe.form.SignUpForm;
import com.seongbindb.recipe.mapper.UserMapper;
import com.seongbindb.recipe.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    private final JavaMailSender javaMailSender;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void processNewUser(SignUpForm signUpForm) {
        User user = insertUser(signUpForm);
        sendSignUpConfirmEmail(user);
    }

    @Override
    public User finByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public int CountAllUser() {
        return userMapper.countAllUser();
    }

    @Override
    public void updateEmailVerified(User user) {
        userMapper.updateEmailVerified(user);
    }

    private User insertUser(SignUpForm signUpForm) {
        String randomId = UUID.randomUUID().toString().substring(0,5); // TODO insert를 위해 임의로 만든 ID
        User user = User.builder()
                .id(randomId)
                .nickname(signUpForm.getNickname())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))// TODO encoding 해야함
                .build();
        user.generateEmailCheckToken();
        userMapper.insertUser(user);
        return user;
    }

    private void sendSignUpConfirmEmail(User user) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Recipe, 회원 가입 인증");
        mailMessage.setText("/check-email-token?token=" + user.getEmailCheckToken()
                + "&email=" + user.getEmail());
        javaMailSender.send(mailMessage);
    }
}



	

