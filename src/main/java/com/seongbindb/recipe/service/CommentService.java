/**
 *
 */
package com.seongbindb.recipe.service;

import com.seongbindb.recipe.vo.RecipeComment;
import com.seongbindb.recipe.vo.User;

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
 * @Date    : 2021. 2. 22. 오전 10:30:21
 * @Version : 1.0
 */
public interface CommentService {

	/**
	 * <pre>
	 * 댓글을 조회한다.
	 * </pre>
	 * @param recipeNo
	 * @param user
	 * @return
	 */
	List<RecipeComment> getCommentsByRecipeNo(Integer recipeNo, User user);

	/**
	 * <pre>
	 * 답글 댓글을 등록한다.
	 * </pre>
	 * @param comment
	 */
	void saveNewComments(RecipeComment comment);

	/**
	 * <pre>
	 * 댓글을 삭제한다.
	 * </pre>
	 * @param commentNo
	 */
	void deleteComment(Integer commentNo);

	/**
	 * <pre>
	 *	본인의 댓글을 수정한다.
	 * </pre>
	 * @param commentNo
	 * @param comment
	 */
	void updateComment(Integer commentNo, String comment);

}
