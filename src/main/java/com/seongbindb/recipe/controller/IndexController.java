package com.seongbindb.recipe.controller;


import com.seongbindb.recipe.annotation.CurrentUser;
import com.seongbindb.recipe.form.UserProfileForm;
import com.seongbindb.recipe.form.SignUpForm;
import com.seongbindb.recipe.service.RecipeService;
import com.seongbindb.recipe.service.UserService;
import com.seongbindb.recipe.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;


/**
 * <pre>
 * 단순 페이지 요청을 처리하여 view를 return하는 컨트롤러
 *
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:27:31
 * @Version : 1.0
 */
@Controller
@RequiredArgsConstructor
public class IndexController {

    private final RecipeService recipeService;
    private final UserService userService;

    @GetMapping("/")
    public String index(@CurrentUser User user, Model model) {
        if(user != null) {
            model.addAttribute(user);
        }
        return "/index";
    }

    /**
     * <pre>
     * 로그인 페이지
     * </pre>
     *
     * @return 로그인 페이지를 조회한다.
     */
    @GetMapping("/login")
    public String login() {
        return "/user/login";
    }


    /**
     * <pre>
     * 회원 가입페이지
     * </pre>
     * @return 메인 페이지를 조회한다.
     */
    @GetMapping("/sign_up")
    public String signUpForm(Model model) {
        // signUpForm 생략가능 camelCase에 의해서
        model.addAttribute("signUpForm", new SignUpForm());
        return "/user/sign_up";
    }

    /**
     * <pre>
     * 프로필 페이지
     * </pre>
     * @return 유저 프로필 페이지를 조회한다.
     */
    @GetMapping("/user/{nickname}")
    public String viewProfile(@PathVariable String nickname, Model model, @CurrentUser User user) {
        User byNickname = userService.findByNickname(nickname);
        if (nickname == null) {
            throw new IllegalArgumentException(nickname + "에 해당하는 사용자가 없습니다.");
        }
        model.addAttribute(byNickname); // 모델명을 지정하지 않아도 괜찮다. 카멜케이스로 적용해서 담겨진다.
        model.addAttribute("isOwner", byNickname.equals(user));
        return "/user/user";

    }

    /**
     * <pre>
     * 로그인 페이지에서 패스워드 없이 로그인하기 클릭시
     * </pre>
     * @return 이메일로 로그인시 페이지를 조회한다.
     */
    @GetMapping("/email_login")
    public String emailLoginForm() {
        return "/user/email_login";
    }


    /**
     * <pre>
     * 메인 페이지
     * </pre>
     *
     * @return 메인 페이지를 조회한다.
     */
    @GetMapping("/recipes")
    public String main() {
        return "main";
    }

    /**
     * <pre>
     * 	레시피의 상세정보 조회
     * </pre>
     *
     * @param recipeNo
     * @param model
     * @param user
     * @return 레시피 상세 정보 페이지
     * @throws Exception
     */
    @GetMapping("/recipes/{recipeNo}")
    public String recipeRead(@PathVariable int recipeNo, Model model, @CurrentUser User user) throws Exception {
        Map<String, Object> recipeInfo = recipeService.getRecipeByRecipeNo(recipeNo, user.getId());
        model.addAttribute("recipeInfo", recipeInfo);
        return "/recipes/recipes_read";
    }


    /**
     * <pre>
     * {recipeNo} 파라미터 값이 있으면 수정페이지, 없다면 등록페이지를 반환하는 컨트롤러
     * </pre>
     *
     * @param recipeNo
     * @return 레시피 등록 페이지, 레시피 수정 페이지
     * @throws Exception
     */
    @GetMapping(value = {"/recipes/update/{recipeNo}", "/recipes/update"})
    public String recipeUpdate(@PathVariable Optional<Integer> recipeNo) throws Exception {
        if (recipeNo.isPresent()) {
            return "/recipes/recipes_update";
        }
        return "/recipes/recipes_insert";
    }
}
