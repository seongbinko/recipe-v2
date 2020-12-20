package com.seongbindb.recipe.service;

import com.seongbindb.recipe.domain.recipes.RecipesDto;
import com.seongbindb.recipe.domain.recipes.RecipeRepository;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@MapperScan(basePackages = "com.seongbindb.recipe.domain.recipes")
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> search() throws Exception {
            List<RecipesDto> recipes = recipeRepository.search();

            Map<String, Object> result = new HashMap<String, Object>();
            result.put("recipes", recipes);

            return result;

    }
}
