package com.seongbindb.recipe.service;

import com.seongbindb.recipe.form.RecipeRegisterform;
import com.seongbindb.recipe.form.Searchform;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Map;

/**
 * <pre>
 * 레시피와 관련된 모든 로직을 처리하는 서비스
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:35:46
 * @Version : 1.0
 */
public interface RecipeService {

    /**
     * <pre>
     * 레시피 등록을 하고, 등록한 게시글의 레시피 번호를 반환한다.
     * </pre>
     *
     * @param request
     * @param userId
     * @param recipeRegisterform
     * @return 레시피 번호
     * @throws IOException
     * @throws Exception
     */
    Integer insertRecipe(MultipartHttpServletRequest request, String userId, RecipeRegisterform recipeRegisterform) throws Exception;

    /**
     * <pre>
     * 레시피 상세정보를 조회할 때 사용하는 로직
     * </pre>
     *
     * @param recipeNo
     * @param userId     (글 작성자라면 수정버튼, 작성자가 아니라면 스크랩 버튼을 보여주기 위한 파라미터)
     * @return
     */
    Map<String, Object> getRecipeByRecipeNo(int recipeNo, String userId);

    /**
     * <pre>
     * 레시피 번호를 가지고 레시피를 삭제한다.
     * </pre>
     *
     * @param recipeNo
     */
    void deleteRecipeByRecipeNo(int recipeNo);

    /**
     * <pre>
     * 스크랩을 저장하고 스크랩 수를 반환한다.
     * </pre>
     *
     * @param id
     * @param recipeNo
     * @return 스크랩 수
     */
    int saveScrap(String id, int recipeNo);

    /**
     * <pre>
     * 스크랩을 삭제하고 스크랩 수를 반환한다.
     * </pre>
     *
     * @param id
     * @param recipeNo
     * @return 스크랩 수
     */
    int deleteScrap(String id, int recipeNo);

    /**
     * <pre>
     * 레시피를 업데이트 한다.
     * </pre>
     *
     * @param request
     * @param recipeRegisterform
     * @throws IOException
     * @throws Exception
     */
    void updateRecipe(MultipartHttpServletRequest request, RecipeRegisterform recipeRegisterform) throws Exception;

    /**
     * <pre>
     * 메인에서 레시피 대표정보들을 조회하기 위한 로직
     * </pre>
     *
     * @param searchform
     * @return
     * @throws Exception
     */
    Map<String, Object> searchMain(Searchform searchform) throws Exception;

}
