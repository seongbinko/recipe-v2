package com.seongbindb.recipe.domain.recipes;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository {

    List<RecipesDto> search();
}
