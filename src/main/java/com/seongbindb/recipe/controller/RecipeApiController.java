package com.seongbindb.recipe.controller;

import com.seongbindb.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class RecipeApiController {

    private final RecipeService recipeService;

    @GetMapping("/recipes")
    public Map<String, Object> searchMain() throws Exception {
        Map<String, Object> result = recipeService.search();
        return result;
    }
}
