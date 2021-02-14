package com.seongbindb.recipe.controller;

import com.seongbindb.recipe.service.UserService;
import com.seongbindb.recipe.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes
public class UserController {

    @Autowired
    private UserService userService;

    /*로그인실행*/
    @RequestMapping(value = "/loginrun", method = RequestMethod.POST)
    public String loginrun(@ModelAttribute("UserVO") User userVO, HttpSession session) throws Exception {
        User user = userService.searchUserReturnUser(userVO); //입력한 아이디 비밀번호를 보낸다
        if (user != null) { //로그인한 정보가 유효하면
            session.setAttribute("USER", user); //세션에 유저 데이터 넣고
            return "redirect:/recipes"; //메인으로 이동
        } else {
            return "redirect:/login?fail=invalidUser"; //유효하지 않은 데이터면 redirect로 값을 보내 유효하지 않다는 문구 띄움
        }
    }
}