package com.seongbindb.recipe.service;


import com.seongbindb.recipe.config.AppProperties;
import com.seongbindb.recipe.dto.UserAccountDto;
import com.seongbindb.recipe.form.SignUpForm;
import com.seongbindb.recipe.form.UserProfileForm;
import com.seongbindb.recipe.mail.EmailMessage;
import com.seongbindb.recipe.mail.EmailService;
import com.seongbindb.recipe.mapper.UserMapper;
import com.seongbindb.recipe.vo.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Component
@RequiredArgsConstructor
public class UserServiceImpl implements UserService , UserDetailsService {

    private final UserMapper userMapper;

    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AppProperties appProperties;

    @Override
    public User processNewUser(SignUpForm signUpForm) {
        User user = insertUser(signUpForm);
        sendSignUpConfirmEmail(user);
        return user;
    }

    @Override
    public User finByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    @Override
    public User findByNickname(String nickname) {
        return userMapper.findByNickname(nickname);
    }

    @Override
    public int CountAllUser() {
        return userMapper.countAllUser();
    }

    @Override
    public void updateEmailVerified(User user) {
        userMapper.updateEmailVerified(user);
    }

    @Override
    public void login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new UserAccountDto(user),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);
    }

    private User insertUser(SignUpForm signUpForm) {
        String randomId = UUID.randomUUID().toString();
        User user = User.builder()
                .id(randomId)
                .nickname(signUpForm.getNickname())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();
        user.generateEmailCheckToken();
        userMapper.insertUser(user);
        return user;
    }
    @Override
    public void sendSignUpConfirmEmail(User user) {
        user.generateEmailCheckToken();
        userMapper.updateEmailCheckTokenWithTime(user);
        String link = "/check_email-token?token=" + user.getEmailCheckToken()
                + "&email=" + user.getEmail();
        String message = "<!DOCTYPE html><html><head><title>레시피</title><meta charset='UTF-8'></head><body><div><div><h2>안녕하세요 " + user.getNickname()
                + "님</h2><p>레시피 서비스 가입을 완료하려면 아래 링크를 클릭하세요</p><div><a href='" + appProperties.getHost() + link
                + "'>가입인증</a><p>링크가 동작하지 않는 경우에는 아래 URL을 브라우저에 복사해서 붙여 넣으세요</p><small>"
                + appProperties.getHost() + link + "</small></div></div><br/><footer><small>RECIPE&copy;2021</small></footer></div></body></html>";

        EmailMessage emailMessage = EmailMessage.builder()
                .to(user.getEmail())
                .subject("레시피, 회원 가입 인증")
                .message(message)
                .build();
        emailService.sendEmail(emailMessage);

    }

    @Override
    public void completedSignUp(User user) {
        user.setEmailVerified(true); // 인증 결과를 업데이트 완료
        updateEmailVerified(user);
        login(user);
    }

    @Override
    public void updateUserProfile(User user, UserProfileForm userProfileForm) {

        modelMapper.map(userProfileForm, user);
//        user.setUrl(userProfileForm.getUrl());
//        user.setOccupation(userProfileForm.getOccupation());
//        user.setLocation(userProfileForm.getLocation());
//        user.setBio(userProfileForm.getBio());
//        user.setUserImage(userProfileForm.getUserImage());
        user.setModDate(LocalDateTime.now());
        userMapper.updateUserProfile(user);
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        //modelMapper.map(newPassword, user);
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setModDate(LocalDateTime.now());
        userMapper.updateUserPassword(user);
    }

    @Override
    public void updateUserNickname(User user, String nickname) {
        user.setNickname(nickname);
        user.setModDate(LocalDateTime.now());
        userMapper.updateUserNickname(user);
    }

    @Override
    public void sendLoginLink(User user) {
        user.generateEmailCheckToken();
        userMapper.updateEmailCheckTokenWithTime(user);
        String link = "/login-by-email?token=" + user.getEmailCheckToken()
                + "&email=" + user.getEmail();
        String message = "<!DOCTYPE html><html><head><title>레시피</title><meta charset='UTF-8'></head><body><div><div><h2>안녕하세요 " + user.getNickname()
                + "님</h2><p>로그인하려면 아래 링크를 클릭하세요</p><div><a href='" + appProperties.getHost() + link
                + "'>Login</a><p>링크가 동작하지 않는 경우에는 아래 URL을 브라우저에 복사해서 붙여 넣으세요</p><small>"
                + appProperties.getHost() + link + "</small></div></div><br/><footer><small>RECIPE&copy;2021</small></footer></div></body></html>";
        EmailMessage emailMessage = EmailMessage.builder()
                .to(user.getEmail())
                .subject("레시피, 로그인 링크")
                .message(message)
                .build();
        emailService.sendEmail(emailMessage);
    }

    // 스프링 시큐리티 로그인 처리
    @Override
    public UserDetails loadUserByUsername(String emailOrNickname) throws UsernameNotFoundException {
        User user = userMapper.findByEmail(emailOrNickname);
        if(user == null) {
            user = userMapper.findByNickname(emailOrNickname);
        }
        if(user == null) {
            throw new UsernameNotFoundException(emailOrNickname);
        }
        return new UserAccountDto(user);
    }
}



	

