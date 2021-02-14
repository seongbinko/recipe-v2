package com.seongbindb.recipe.controller;


import com.seongbindb.recipe.service.RecipeService;
import com.seongbindb.recipe.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

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
public class IndexController {

    @Autowired
    RecipeService recipeService;

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
     * @param session
     * @return 레시피 상세 정보 페이지
     * @throws Exception
     */
    @GetMapping("/recipes/{recipeNo}")
    public String recipeRead(@PathVariable int recipeNo, Model model, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("USER");
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

    //로그인
    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    //로그아웃
    @GetMapping("/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        ModelAndView mv = new ModelAndView("redirect:/login");
        return mv;
    }
}
