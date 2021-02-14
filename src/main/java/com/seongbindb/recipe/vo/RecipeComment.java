/**
 *
 */
package com.seongbindb.recipe.vo;

import com.seongbindb.recipe.utils.DateUtils;
import lombok.*;
import org.apache.ibatis.type.Alias;

import java.util.Date;

/**
 * <pre>
 *
 *
 * </pre>
 *
 *
 * @Company : SeongbinDB
 * @Author  : Seongbin Ko
 * @Date    : 2021. 2. 22. 오전 10:36:30
 * @Version : 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipeComment {

	private Integer commentLevel;
	private Integer recipeNo;
	private Integer commentNo;
	private Integer commentParentNo;
	private String commentContent;
	private String commentWriter;
	private Date commentCreateDate;
	private Date commentModDate;
	private String role;
	private String commentStatus;
	private String fullDate;

	public void setFullDate(Date date) {
		this.fullDate = DateUtils.dateToStringWithTime(date);
	}

}
