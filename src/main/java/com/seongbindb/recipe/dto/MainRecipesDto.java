/**
 *
 */
package com.seongbindb.recipe.dto;

import com.seongbindb.recipe.utils.DateUtils;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * <pre>
 * 메인페이지에서 레시피 조회시 필요한 정보들을 담는 MODEL
 * </pre>
 *
 *
 * @Company : SeongbinDB
 * @Author  : Seongbin Ko
 * @Date    : 2021. 2. 14. 오전 11:07:18
 * @Version : 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MainRecipesDto {

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
    private String fullDate;

    public void setFullDate(Date fullDate) {
        this.fullDate = DateUtils.dateToStringWithTime(fullDate);
    }
}
