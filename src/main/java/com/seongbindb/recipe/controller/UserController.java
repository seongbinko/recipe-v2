package com.seongbindb.recipe.controller;

import com.seongbindb.recipe.form.SignUpForm;
import com.seongbindb.recipe.form.SignUpFormValidator;
import com.seongbindb.recipe.service.UserService;
import com.seongbindb.recipe.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;


    private final SignUpFormValidator signUpFormValidator;

    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
        // signUpForm 생략가능 camelCase에 의해서
        model.addAttribute("signUpForm", new SignUpForm());
        return "/user/sign-up";
    }

    // signUpForm 을 받을때 403 검증도 하고 validator 검증도 한다.
    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors, Model model) { // @ModelAttribute 복합(여러값)들을 가진 객체를 받을 때 사용을 하지만 파라미터 가 없어도 되기 때문에 생략이 가능하다.
        if(errors.hasErrors()) {
            Map<String,String> map = new HashMap<>();
            for(FieldError error :errors.getFieldErrors()) {
                map.put(error.getField() , error.getDefaultMessage());
            }
            model.addAttribute("map",map);
            return "/user/sign-up";
        }
        userService.processNewUser(signUpForm);
        return "redirect:/";
    }

    @GetMapping("/check-email-token")
    public String checkEmailToken(String token, String email, Model model) {
        User user = userService.finByEmail(email);
        String view = "/user/checked-email";
        if (user == null) {
            model.addAttribute("error", "wrong.email");
            return view;
        }

        if(!user.getEmailCheckToken().equals(token)) {
            model.addAttribute("error", "wrong.token");
            return view;
        }
        completedSignUp(user);
        model.addAttribute("numberOfUser", userService.CountAllUser());
        model.addAttribute("nickname", user.getNickname());
        return view;
    }

    private void completedSignUp(User user) {
        user.setEmailVerified(true); // TODO eamil 인증 결과를 업데이트 쳐야할 수 도 있음
        userService.updateEmailVerified(user);
    }
}