package com.seongbindb.recipe.mapper;

import com.seongbindb.recipe.vo.RecipeCategory;
import org.springframework.stereotype.Repository;


/**
 * <pre>
 * RECIPE_CATEGORY 테이블에 접근하는 MAPPER
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:37:25
 * @Version : 1.0
 */
@Repository
public interface CategoryMapper {

    /**
     * <pre>
     * RECIPE_CATEGORY
     * 카테고리명으로 카테고리를 조회한다.
     * </pre>
     *
     * @param categoryName
     * @return RecipeCategory
     */
    RecipeCategory selectCategoryByName(String categoryName);

    /**
     * <pre>
     * RECIPE_CATEGORY
     * 레시피번호로 조회한 레시피의 카테고리 정보를 조회한다.
     * </pre>
     *
     * @param recipeNo
     * @return RecipeCategory
     */
    RecipeCategory selectRecipeCategoryByCategoryNo(int recipeNo);

}
