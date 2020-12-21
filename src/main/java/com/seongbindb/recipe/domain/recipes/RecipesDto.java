package com.seongbindb.recipe.domain.recipes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipesDto {

    private int recipeNo;
    private String userId;
    private String recipeName;
    private Date createDate;
    private Date modDate;
    private String status;
    private int categoryNo;
}
