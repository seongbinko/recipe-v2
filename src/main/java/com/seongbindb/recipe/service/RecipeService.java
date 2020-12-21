package com.seongbindb.recipe.service;

import com.seongbindb.recipe.domain.recipes.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@MapperScan(basePackages = "com.seongbindb.recipe.domain.recipes")
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Transactional(readOnly = true)
    public Map<String, Object> search() {

        Map<String, Object> result = new HashMap();
        result.put("recipes", recipeRepository.search());
        return result;
    }
}
