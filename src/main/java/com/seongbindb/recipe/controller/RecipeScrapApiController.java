package com.seongbindb.recipe.controller;

import com.seongbindb.recipe.service.RecipeService;
import com.seongbindb.recipe.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * <pre>
 * 레시피 스크랩 등록, 삭제 Api
 * *
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:26:43
 * @Version : 1.0
 */
@RestController
public class RecipeScrapApiController {

    @Autowired
    RecipeService recipeService;

    /**
     * <pre>
     * 사용자가 레시피 상세정보 페이지에서 스크랩 버튼을 누르면 스크랩 등록을 처리
     * </pre>
     *
     * @param recipeNo
     * @param session
     * @return 스크랩 수
     * @throws Exception
     */
    @PostMapping("/api/recipes/scraps/{recipeNo}")
    public int saveScarp(@PathVariable int recipeNo, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("USER");
        return recipeService.saveScrap(user.getId(), recipeNo);
    }

    /**
     * <pre>
     * 스크랩 취소 버튼을 누르면 스크랩 취소 수행
     * </pre>
     *
     * @param recipeNo
     * @param session
     * @return 스크랩수
     * @throws Exception
     */
    @DeleteMapping("/api/recipes/scraps/{recipeNo}")
    public int deleteScrap(@PathVariable int recipeNo, HttpSession session) throws Exception {
        User user = (User) session.getAttribute("USER");
        return recipeService.deleteScrap(user.getId(), recipeNo);
    }
}
