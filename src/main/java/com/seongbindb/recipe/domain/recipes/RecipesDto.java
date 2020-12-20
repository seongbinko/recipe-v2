package com.seongbindb.recipe.domain.recipes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipesDto {

    private int rn;
    private int recipeNo;
    private String userId;
    private String nickName;
    private String recipeName;
    private Date createDate;
    private Date modDate;
    private String status;
    private int categoryNo;
    private int cnt;
    private String thumbnailImg;

}
