package com.seongbindb.recipe.vo;

import lombok.*;
import org.apache.ibatis.type.Alias;

/**
 * <pre>
 * 레시피 카테고리 정보를 담는 MODEL
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:29:45
 * @Version : 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipeCategory {
    private Integer recipeCategoryNo;
    private String recipeCategoryName;
}
