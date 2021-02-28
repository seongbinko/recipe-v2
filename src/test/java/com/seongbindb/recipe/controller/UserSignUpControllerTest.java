package com.seongbindb.recipe.controller;

import com.seongbindb.recipe.mail.EmailMessage;
import com.seongbindb.recipe.mail.EmailService;
import com.seongbindb.recipe.mapper.UserMapper;
import com.seongbindb.recipe.vo.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.unauthenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserSignUpControllerTest {

  @Autowired MockMvc mockMvc;

  @Autowired UserMapper userMapper;

  @MockBean EmailService emailService;


  @AfterEach
  void afterEach() {
    userMapper.deleteAll();
  }

  @DisplayName("인증 메일 확인 - 입력값 정상")
  @Test
  void checkEmailToken() throws Exception {

    mockMvc.perform(post("/sign_up")
            .param("nickname", "mailtest")
            .param("email", "mailtest@email.com")
            .param("password", "12345678")
            .with(csrf()));

    User user = userMapper.findByEmail("mailtest@email.com");

    mockMvc.perform(get("/check_email-token")
            .param("token", user.getEmailCheckToken())
            .param("email", user.getEmail()))
            .andExpect(status().isOk())
            .andExpect(model().attributeDoesNotExist("error"))
            .andExpect(model().attributeExists("nickname"))
            .andExpect(model().attributeExists("numberOfUser"))
            .andExpect(view().name("/user/checked_email"))
            .andExpect(authenticated());
  }

  @DisplayName("인증 메일 확인 - 입력값 오류")
  @Test
  void checkEmailToken_with_wrong_input() throws Exception {

    mockMvc.perform(get("/check_email-token")
            .param("token", "temp")
            .param("email", "email@email.com"))
            .andExpect(status().isOk())
            .andExpect(model().attributeExists("error"))
            .andExpect(view().name("/user/checked_email"))
            .andExpect(unauthenticated());
  }

  @DisplayName("회원 가입 화면 보이는지 테스트")
  @Test
  void signUpForm() throws Exception {
      mockMvc.perform(get("/sign_up"))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(view().name("/user/sign_up"))
              .andExpect(model().attributeExists("signUpForm"))
              .andExpect(unauthenticated());
  }

  @DisplayName("회원 가입 처리 - 입력값 오류")
  @Test
  void signUpSubmit_with_wrong_input() throws Exception {
      mockMvc.perform(post("/sign_up")
            .param("nickname", "seongbin")
            .param("email", "email...")
            .param("password", "12345")
            .with(csrf()))
            .andExpect(status().isOk()) //잘못된 데이터지만 프론트에서 오기때문에 이값은 ok
            .andExpect(view().name("/user/sign_up"))
            .andExpect(unauthenticated());
  }
  @DisplayName("회원 가입 처리 - 입력값 정상")
  @Test
  void signUpSubmit_with_correct_input() throws Exception {
    mockMvc.perform(post("/sign_up")
            .param("nickname", "seongbin")
            .param("email", "seongbin@email.com")
            .param("password", "12345678")
            .with(csrf()))
            .andExpect(status().is3xxRedirection())
            .andExpect(view().name("redirect:/"))
            .andExpect(authenticated().withUsername("seongbin"));

    User user = userMapper.findByEmail("seongbin@email.com");
    assertNotNull(user);
    assertNotEquals(user.getPassword(), "12345678");
    assertNotNull(user.getEmailCheckToken());
    then(emailService).should().sendEmail(any(EmailMessage.class));
  }
}