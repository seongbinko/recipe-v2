package com.seongbindb.recipe.mapper;

import com.seongbindb.recipe.dto.MainRecipesDto;
import com.seongbindb.recipe.form.Searchform;
import com.seongbindb.recipe.vo.Recipe;
import com.seongbindb.recipe.vo.RecipeDetail;
import com.seongbindb.recipe.vo.RecipeScrap;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>
 * RECIPE TABLE, RECIPE_DETAIL_TABLE,RECIPE_SCARP TABLE에 접근하기 위한 MAPPER
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:37:49
 * @Version : 1.0
 */
@Repository
public interface RecipeMapper {

    /**
     * <pre>
     * RECIPE TABLE
     * 레시피 정보를 등록한다.
     * </pre>
     *
     * @param recipe
     */
    void insertRecipe(Recipe recipe);

    /**
     * <pre>
     * RECIPE_DETAIL
     * 레시피 상세정보(스텝)을 등록한다.
     * </pre>
     *
     * @param detail
     */
    void insertRecipeDetail(RecipeDetail detail);

    /**
     * <pre>
     * RECIPE TABLE에서
     * 레시피 번호에 해당하는 Recipe를 조회한다.
     * </pre>
     *
     * @param recipeNo
     * @return
     */
    Recipe selectRecipeByRecipeNo(int recipeNo);

    /**
     * <pre>
     * RECIPE_DETAIL
     * 레시피번호로 레시피 상세정보(리스트) 스텝들을 List<RecipeDetail> 조회 반환한다.
     * </pre>
     *
     * @param recipeNo
     * @return List<RecipeDetail>
     */
    List<RecipeDetail> selectRecipeDetailsByRecipeNo(int recipeNo);

    /**
     * <pre>
     * RECIPE_SCARP
     * 레시피번호에 해당하는 레시피 스크랩 정보 리스트를 조회해서 반환한다.
     * </pre>
     *
     * @param recipeNo
     * @return List<RecipeScrap>
     */
    List<RecipeScrap> selectRecipeScrapsByRecipeNo(int recipeNo);

    /**
     * <pre>
     * RECIPE
     * 레시피번호에 해당하는 레시피 테이블의 레시피를 삭제한다.
     * </pre>
     *
     * @param recipeNo
     */
    void deleteRecipeByRecipeNo(int recipeNo);

    /**
     * <pre>
     * RECIPE_DETAIL
     * 레시피번호에 해당하는 레시피 상세정보 테이블 (스텝) 리스트 들을 삭제한다.
     * </pre>
     *
     * @param recipeNo
     */
    void deleteRecipeDetailByRecipeNo(int recipeNo);

    /**
     * <pre>
     * RECIPE_SCRAP
     * 레시피번호에 해당하는 스크랩한 모든 스크랩 정보를 삭제한다.
     * </pre>
     *
     * @param recipeNo
     */
    void deleteRecipeScrapByNo(int recipeNo);

    /**
     * <pre>
     * RECIPE_SCRAP
     * 레시피 스크랩을 등록한다.
     * </pre>
     *
     * @param recipeScrap
     */
    void insertRecipeScrap(RecipeScrap recipeScrap);

    /**
     * <pre>
     * RECIPE_SCRAP
     * 레시피번호에 해당하는 총 스크랩수를 조회 반환한다.
     * </pre>
     *
     * @param recipeNo
     * @return 스크랩수
     */
    int countScrapByRecipeNo(int recipeNo);

    /**
     * <pre>
     * RECIPE_SCRAP
     * 내가 등록한 레시피스크랩을 삭제한다.
     * </pre>
     *
     * @param userId
     * @param recipeNo
     */
    void deleteMyRecipeScraps(@Param("userId") String userId, @Param("recipeNo") int recipeNo);

    /**
     * <pre>
     * RECIPE_TABLE
     * 레시피를 업데이트한다.
     * </pre>
     *
     * @param recipe
     */
    void updateRecipe(Recipe recipe);

    /**
     * <pre>
     * RECIPE_DETAIL
     * 레시피번호에 해당하는 레시피 상세번호 리스트(스텝)를 조회한다.
     * </pre>
     *
     * @param reicpeNo
     * @return 레시피상세번호 리스트
     */
    List<Integer> selectRecipeDetailsNoByRecipeNo(int reicpeNo);

    /**
     * <pre>
     * RECIPE_DETAIL
     * 레시피 삭제를 수행할 때 레시피 상세번호로 레시피 스텝을 삭제한다.
     * </pre>
     *
     * @param orginDetailNo
     */
    void deleteRecipeDetailByDetailNo(int orginDetailNo);

    /**
     * <pre>
     * RECIPE_DETAIL
     * 레시피상세번호로 한개의 레시피 상세정보를 조회한다.
     * </pre>
     *
     * @param recipeDetailNo
     * @return RecipeDetail
     */
    RecipeDetail selectOneRecipeDetailByRecipeDetailNo(int recipeDetailNo);

    /**
     * <pre>
     * RECIPE_DETAIL
     * 레시피 상세정보(스텝)를 업데이트 한다.
     * </pre>
     *
     * @param detail
     */
    void updateRecipeDetail(RecipeDetail detail);

    /**
     * <pre>
     * RECIPE
     * 페이징 처리를 위해 조건에 만족하는 레시피개수를 구한다.
     * </pre>
     *
     * @param searchform
     * @return 조건에 만족하는 레시피 개수
     */
    int searchRecipeCount(Searchform searchform);

    /**
     * <pre>
     * RECIPE ,RECIPE_DETAIL ,RECIPE_SCRAP
     * 검색 조건을 담은 searchform을 바탕으로 조회된 레시피정보 리스트를 반환한다.
     * </pre>
     *
     * @param searchform
     * @return List<MainRecipesDto>
     */
    List<MainRecipesDto> searchRecipesBysearchForm(Searchform searchform);


    /**
     * <pre>
     * 레시피 번호에 해당하는 댓글들을 삭제한다.
     * </pre>
     * @param recipeNo
     */
    void deleteRecipeCommentByNo(int recipeNo);
}
