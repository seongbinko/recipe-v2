package com.seongbindb.recipe.controller;

import com.seongbindb.recipe.annotation.CurrentUser;
import com.seongbindb.recipe.form.UserNicknameForm;
import com.seongbindb.recipe.form.UserPasswordForm;
import com.seongbindb.recipe.validator.UserNicknameFormValidator;
import com.seongbindb.recipe.validator.UserPasswordFormValidator;
import com.seongbindb.recipe.form.UserProfileForm;
import com.seongbindb.recipe.service.UserService;
import com.seongbindb.recipe.vo.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserProfileController {

    static final String USER_UPDATE_URL = "/user/update";
    static final String USER_UPDATE_PROFILE_API = "/user/update/profile";
    static final String USER_UPDATE_PASSWORD_API = "/user/update/password";
    static final String USER_UPDATE_NICKNAME_API = "/user/update/nickname";
    static final String USER_UPDATE_VIEW_NAME = "/user/user_update";

    private final UserService userService;
    private final ModelMapper modelMapper;
    private final UserNicknameFormValidator userNicknameFormValidator;

    @InitBinder("userPasswordForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new UserPasswordFormValidator());
    }

    @InitBinder("userNicknameForm")
    public void initBinder2(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(userNicknameFormValidator);
    }


    @GetMapping(USER_UPDATE_URL)
    public String userProfileForm(@CurrentUser User user, Model model) {
        model.addAttribute(user);
        //model.addAttribute(new UserProfileForm(user));
        //model.addAttribute(modelMapper.map(user, UserProfileForm.class)); ModelMapper사용시
        return USER_UPDATE_VIEW_NAME;
    }

    @PostMapping(USER_UPDATE_PROFILE_API)
    public String updateUserProfile(@CurrentUser User user, @Valid @ModelAttribute UserProfileForm userProfileForm,
                                    Errors errors, Model model, RedirectAttributes attributes) {
        if(errors.hasErrors()) {
            Map<String,String> map = new HashMap<>();
            for(FieldError error :errors.getFieldErrors()) {
                map.put(error.getField() , error.getDefaultMessage());
            }
            model.addAttribute("errors",map);
            model.addAttribute("tabInfo", "profile");
            model.addAttribute(user);
            return USER_UPDATE_VIEW_NAME;
        }
        userService.updateUserProfile(user, userProfileForm);
        // model.addAttribute("user",updatedUser);
        attributes.addFlashAttribute("tabInfo","profile");
        attributes.addFlashAttribute("message", "프로필을 수정했습니다.");

        return "redirect:" + USER_UPDATE_URL;
    }

    @PostMapping(USER_UPDATE_PASSWORD_API)
    public String updateUserPassword(@CurrentUser User user, @Valid UserPasswordForm userPasswordForm, Errors errors ,
                                     Model model, RedirectAttributes attributes) {
        if(errors.hasErrors()) {
            Map<String,String> map = new HashMap<>();
            for(FieldError error :errors.getFieldErrors()) {
                map.put(error.getField() , error.getDefaultMessage());
            }
            model.addAttribute("errors",map);
            model.addAttribute("tabInfo", "password");
            model.addAttribute(user);
            return USER_UPDATE_VIEW_NAME;
        }
        userService.updatePassword(user, userPasswordForm.getNewPassword());
        attributes.addFlashAttribute("tabInfo", "password");
        attributes.addFlashAttribute("message", "비밀번호를 변경하였습니다.");

        return "redirect:" + USER_UPDATE_URL;
    }
    @PostMapping(USER_UPDATE_NICKNAME_API)
    public String updateUserNickname(@CurrentUser User user, @Valid UserNicknameForm userNicknameForm, Errors errors,
                                     Model model, RedirectAttributes attributes) {
        if(errors.hasErrors()) {
            Map<String,String> map = new HashMap<>();
            for(FieldError error :errors.getFieldErrors()) {
                map.put(error.getField() , error.getDefaultMessage());
            }
            model.addAttribute("errors",map);
            model.addAttribute("tabInfo", "account");
            model.addAttribute(user);
            return USER_UPDATE_VIEW_NAME;
        }
        userService.updateUserNickname(user, userNicknameForm.getNickname());
        attributes.addFlashAttribute("tabInfo", "account");
        attributes.addFlashAttribute("message", "닉네임을 변경하였습니다.");

        return "redirect:" + USER_UPDATE_URL;
    }
}
