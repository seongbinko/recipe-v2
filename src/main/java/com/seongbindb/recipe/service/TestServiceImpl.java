/**
 *
 */
package com.seongbindb.recipe.service;

import com.seongbindb.recipe.mapper.RecipeMapper;
import com.seongbindb.recipe.vo.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <pre>
 *
 *
 * </pre>
 *
 *
 * @Company : SeongbinDB
 * @Author  : Seongbin Ko
 * @Date    : 2021. 2. 14. 오전 11:07:10
 * @Version : 1.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private RecipeMapper recipeMapper;

    public void setRecipeMapper(RecipeMapper recipeMapper) {
        this.recipeMapper = recipeMapper;
    }

    @Override
    public void doInsert(Recipe recipe) {
        recipeMapper.insertRecipe(recipe);
    }

}
