package com.seongbindb.recipe.vo;

import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * <pre>
 * 레시피의 상세정보 (이미지, 스텝)등을 담기 위한 MODEL
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:30:14
 * @Version : 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipeDetail {

    private Integer recipeDetailNo;
    private Integer recipeNo;
    private Integer recipeDetailStep;
    private String content;
    private String recipeDetailImg;

}
