/**
 *
 */
package com.seongbindb.recipe.mapper;

import com.seongbindb.recipe.vo.RecipeComment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <pre>
 *
 *
 * </pre>
 *
 *
 * @Company : SeongbinDB
 * @Author  : Seongbin Ko
 * @Date    : 2021. 2. 22. 오전 10:33:42
 * @Version : 1.0
 */
@Repository
public interface CommentMapper {

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param recipeNo
	 * @return
	 */
	List<RecipeComment> selectCommentsByRecipeNo(Integer recipeNo);

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param comment
	 */
	void insertNewComment(RecipeComment comment);

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param
	 * @return
	 */
	RecipeComment selectCommentByNo(Integer commentNo);

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param commentNo
	 * @return
	 */
	int getCountRelatedComments(Integer commentNo);

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param commentNo
	 */
	void deleteComment(Integer commentNo);

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param comment
	 */
	void updateDisableComment(RecipeComment comment);

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param recipeComment
	 */
	void updateComment(RecipeComment recipeComment);

}
