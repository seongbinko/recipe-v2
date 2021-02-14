package com.seongbindb.recipe.vo;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * <pre>
 * 레시피 스크랩 정보를 담기위한 MODEL
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:30:50
 * @Version : 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipeScrap {

    private Integer recipeScrapNo;
    private String userId;
    private Integer recipeNo;
    private String recipeName;
    private Date recipeScrapDate;

    public RecipeScrap(String userId, Integer recipeNo) {
        super();
        this.userId = userId;
        this.recipeNo = recipeNo;
    }
}
