package com.seongbindb.recipe.vo;

import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * <pre>
 * 레시피 정보를 담는 MODEL
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:28:22
 * @Version : 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Recipe {

    private Integer recipeNo;
    private String userId;
    private String nickName;
    private String recipeName;
    private Date recipeCreateDate;
    private Date recipeModDate;
    private String recipeStatus;
    private Integer recipeCategoryNo;

}
