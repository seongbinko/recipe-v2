package com.seongbindb.recipe.controller;

import com.seongbindb.recipe.annotation.CurrentUser;
import com.seongbindb.recipe.form.SignUpForm;
import com.seongbindb.recipe.validator.SignUpFormValidator;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class UserSignUpController {


    private final UserService userService;

    private final SignUpFormValidator signUpFormValidator;

    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(signUpFormValidator);
    }

    // signUpForm 을 받을때 403 검증도 하고 validator 검증도 한다.
    @PostMapping("/sign_up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors, Model model) { // @ModelAttribute 복합(여러값)들을 가진 객체를 받을 때 사용을 하지만 파라미터 가 없어도 되기 때문에 생략이 가능하다.
        if(errors.hasErrors()) {
            Map<String,String> map = new HashMap<>();
            for(FieldError error :errors.getFieldErrors()) {
                map.put(error.getField() , error.getDefaultMessage());
            }
            model.addAttribute("errors",map);
            return "/user/sign_up";
        }
        User user = userService.processNewUser(signUpForm);
        userService.login(user);
        return "redirect:/";
    }

    @GetMapping("/check_email")
    public String checkEmail(@CurrentUser User user, Model model) {
        model.addAttribute("email", user.getEmail());
        return "/user/check_email";
    }

    // 이메일인증을 악위적으로 하는 것을 막기 위함
    @GetMapping("/resend-confirm-email")
    public String resendConfirmEmail(@CurrentUser User user, Model model) {
        if(!user.canSendConfirmEmail()) {
            model.addAttribute("error", "인증 이메일은 1시간에 한번만 전송할 수 있습니다.");
            model.addAttribute("email", user.getEmail());
            return "/user/check_email";
        }
        userService.sendSignUpConfirmEmail(user);
        return "redirect:/";
    }


    @GetMapping("/check_email-token")
    public String checkEmailToken(String token, String email, Model model) {
        User user = userService.finByEmail(email);
        String view = "/user/checked_email";
        if (user == null) {
            model.addAttribute("error", "wrong.email");
            return view;
        }

        if(!user.isValidToken(token)) {
            model.addAttribute("error", "wrong.token");
            return view;
        }
        userService.completedSignUp(user);

        model.addAttribute("numberOfUser", userService.CountAllUser());
        model.addAttribute("nickname", user.getNickname());
        return view;
    }

    @PostMapping("/email_login")
    public String sendEmailLoginLink(String email, Model model, RedirectAttributes attributes) {
        User user = userService.finByEmail(email);
        if(user == null) {
            model.addAttribute("error", "유효한 이메일 주소가 아닙니다.");
            return "/user/email_login";
        }
        if(!user.canSendConfirmEmail()) {
            model.addAttribute("error", "이메일 로그인은 1시간 뒤에 사용할 수 있습니다.");
            return "/user/email_login";
        }
        userService.sendLoginLink(user);
        attributes.addFlashAttribute("message", "이메일 인증 메일을 발송했습니다.");
        return "redirect:/email_login";
    }

    @GetMapping("/login-by-email")
    public String loginByEmail(String token, String email, Model model) {
        User user = userService.finByEmail(email);
        String view = "/user/logged_in_by_email";
        if(user == null || !user.isValidToken(token)) {
            model.addAttribute("error", "로그인할 수 없습니다.");
            return view;
        }
        userService.login(user);
        return view;
    }

}