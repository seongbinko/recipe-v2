package com.seongbindb.recipe.controller;

import com.seongbindb.recipe.form.SignUpForm;
import com.seongbindb.recipe.mapper.UserMapper;
import com.seongbindb.recipe.service.UserService;
import com.seongbindb.recipe.vo.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserProfileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired PasswordEncoder passwordEncoder;

    @BeforeEach
    void beforeEach() {
        SignUpForm signUpForm = new SignUpForm();
        signUpForm.setNickname("seongbin");
        signUpForm.setEmail("seongbin@email.com");
        signUpForm.setPassword("12345678");
        userService.processNewUser(signUpForm);
    }

    @AfterEach
    void afterEach() {
        userMapper.deleteAll();
    }

    @WithUserDetails(value = "seongbin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("프로필 조회화면")
    @Test
    void updateProfileForm() throws Exception {
        mockMvc.perform(get(UserProfileController.USER_UPDATE_URL))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name(UserProfileController.USER_UPDATE_VIEW_NAME));
    }

    @WithUserDetails(value = "seongbin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("프로필 수정 하기 - 입력값 정상")
    @Test
    void updateProfile() throws Exception {
        String bio = "짧은 소개를 수정하는 경우.";
        mockMvc.perform(post(UserProfileController.USER_UPDATE_PROFILE_API)
                .param("bio", bio)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(UserProfileController.USER_UPDATE_URL))
                .andExpect(flash().attributeExists("message"));
        User user = userService.findByNickname("seongbin");
        assertEquals(bio, user.getBio());
    }

    @WithUserDetails(value = "seongbin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @DisplayName("프로필 수정 하기 - 입력값 에러")
    @Test
    void updateProfile_error() throws Exception {
        String bio = "길게 소개를 수정하는 경우.길게 소개를 수정하는 경우.길게 소개를 수정하는 경우.길게 소개를 수정하는 경우.길게 소개를 수정하는 경우.길게 소개를 수정하는 경우.";
        mockMvc.perform(post(UserProfileController.USER_UPDATE_PROFILE_API)
                .param("bio", bio)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(UserProfileController.USER_UPDATE_VIEW_NAME))
                .andExpect(model().hasErrors())
                .andExpect(model().attributeExists("errors"))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attributeExists("userProfileForm"));
        User user = userService.findByNickname("seongbin");
        assertNull(user.getBio());
    }

    @DisplayName("새 패스워드로 변경 - 입력값 정상")
    @WithUserDetails(value = "seongbin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    void updatePassword() throws Exception {
        String newPassword = "00000000";
        mockMvc.perform(post(UserProfileController.USER_UPDATE_PASSWORD_API)
                .param("newPassword", newPassword)
                .param("newPasswordConfirm", newPassword)
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(UserProfileController.USER_UPDATE_URL))
                .andExpect(flash().attributeExists("tabInfo"))
                .andExpect(flash().attributeExists("message"));
        User user = userService.findByNickname("seongbin");
        // assertEquals(user.getPassword(), passwordEncoder.encode(newPassword));
        assertTrue(passwordEncoder.matches("00000000", user.getPassword()));
    }

    @DisplayName("새 패스워드로 변경 - 패스워드 불일치")
    @WithUserDetails(value = "seongbin" , setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    void updatePassword_error() throws Exception {
        mockMvc.perform(post(UserProfileController.USER_UPDATE_PASSWORD_API)
                .param("newPassword", "00000000")
                .param("newPasswordConfirm", "00000001")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(UserProfileController.USER_UPDATE_VIEW_NAME))
                .andExpect(model().attributeExists("errors"))
                .andExpect(model().attributeExists("tabInfo"))
                .andExpect(model().attributeExists("user"));
    }

    @DisplayName("닉네임 변경 - 정상")
    @WithUserDetails(value = "seongbin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    void updateNickname() throws Exception {
        mockMvc.perform(post(UserProfileController.USER_UPDATE_NICKNAME_API)
                    .param("nickname", "koseongbin")
                    .with(csrf()))
                    .andExpect(status().is3xxRedirection())
                    .andExpect(redirectedUrl(UserProfileController.USER_UPDATE_URL))
                    .andExpect(flash().attributeExists("tabInfo"))
                    .andExpect(flash().attributeExists("message"));
        User user = userService.findByNickname("koseongbin");
        assertNotNull(user);
    }
    @DisplayName("닉네임 변경 - 에러")
    @WithUserDetails(value = "seongbin", setupBefore = TestExecutionEvent.TEST_EXECUTION)
    @Test
    void updateNickname_error() throws Exception {
        mockMvc.perform(post(UserProfileController.USER_UPDATE_NICKNAME_API)
                .param("nickname", "seongbin")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name(UserProfileController.USER_UPDATE_VIEW_NAME))
                .andExpect(model().attributeExists("errors"))
                .andExpect(model().attributeExists("tabInfo"))
                .andExpect(model().attributeExists("user"));
    }
}