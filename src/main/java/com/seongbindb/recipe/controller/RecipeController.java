package com.seongbindb.recipe.controller;

import com.seongbindb.recipe.annotation.CurrentUser;
import com.seongbindb.recipe.form.RecipeRegisterform;
import com.seongbindb.recipe.form.Searchform;
import com.seongbindb.recipe.service.RecipeService;
import com.seongbindb.recipe.service.UserService;
import com.seongbindb.recipe.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * <pre>
 * 레시피 조회(검색), 등록 , 수정, 삭제를 수행하는 컨트롤러
 *
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:27:09
 * @Version : 1.0
 */
@RestController
@RequiredArgsConstructor
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    UserService userService;

    /**
     * <pre>
     * 	레시피 검색 api
     * </pre>
     *
     * @param searchform (페이지번호,카테고리번호,검색어,정렬기준, 페이지 처리를 위한 인덱스)
     * @return 조건에 맞게 조회된 값을 반환
     * @throws Exception
     */
    @PostMapping("/recipes")
    public Map<String, Object> searchMain(@RequestBody Searchform searchform) throws Exception {
        Map<String, Object> result = recipeService.searchMain(searchform);
        return result;
    }


    /**
     * <pre>
     * 레시피 등록 api
     * </pre>
     *
     * @param recipeRegisterform (레시피번호, 레시피제목, 카테고리명,레시피상세번호,레시피 내용, 이미지)
     * @param request
     * @param mav
     * @return 레시피 등록 후 작성한 레시피 상세정보 url을 반환한다.
     * @throws Exception
     */
    @PostMapping("/api/recipes")
    public ModelAndView save(RecipeRegisterform recipeRegisterform, @CurrentUser User user,  MultipartHttpServletRequest request, ModelAndView mav) throws Exception {
        int recipeNo = recipeService.insertRecipe(request, user.getId(), recipeRegisterform);
        mav.setViewName("redirect:/recipes/" + recipeNo);
        return mav;
    }


    /**
     * <pre>
     * 레시피 수정 api
     * </pre>
     *
     * @param recipeNo
     * @param recipeRegisterform (레시피번호, 레시피제목, 카테고리명,레시피상세번호,레시피 내용, 이미지)
     * @param request
     * @param mav
     * @return 레시피 수정 후, 수정한 레시피 페이지로 이동한다.
     * @throws Exception
     */
    @PostMapping("/api/recipes/{recipeNo}")
    public ModelAndView update(@PathVariable int recipeNo,
                               RecipeRegisterform recipeRegisterform,
                               MultipartHttpServletRequest request,
                               ModelAndView mav) throws Exception {
        recipeService.updateRecipe(request, recipeRegisterform);
        mav.setViewName("redirect:/recipes/" + recipeNo);
        return mav;
    }


    /**
     * <pre>
     * 레시피 삭제 api
     * </pre>
     *
     * @param recipeNo
     * @throws Exception
     */
    @DeleteMapping("/api/recipes/{recipeNo}")
    public void delete(@PathVariable int recipeNo) throws Exception {
        recipeService.deleteRecipeByRecipeNo(recipeNo);
    }


    /**
     * <pre>
     * 레시피 상세정보 페이지에서 수정 버튼을 클릭시 동작하는 컨트롤러, 수정페이지로 이동한다.
     * </pre>
     *
     * @param recipeNo
     * @param user
     * @return 레시피 수정 페이지
     * @throws Exception
     */
    @GetMapping("/api/recipes/{recipeNo}")
    public Map<String, Object> searchUpdatePage(@PathVariable int recipeNo, @CurrentUser User user) throws Exception {
        return recipeService.getRecipeByRecipeNo(recipeNo, user.getId());
    }

}
