package com.seongbindb.recipe.service;

import com.seongbindb.recipe.vo.RecipeCategory;

/**
 * <pre>
 * 레시피 카테고리와 관련된 로직을 처리하는 Service
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:35:35
 * @Version : 1.0
 */
public interface CategoryService {

    /**
     * <pre>
     * 카테고리명으로 카테고리 정보를 조회한다.
     * </pre>
     *
     * @param categoryName
     * @return RecipeCategory
     */
    RecipeCategory getCategoryByName(String categoryName);
}
