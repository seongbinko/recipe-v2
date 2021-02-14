/**
 *
 */
package com.seongbindb.recipe.controller;

import com.seongbindb.recipe.service.CommentService;
import com.seongbindb.recipe.vo.RecipeComment;
import com.seongbindb.recipe.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <pre>
 * 레시피 상세화면의 댓글기능을 담당하는 Api
 * 댓글 조회, 등록, 수정, 삭제 기능
 * </pre>
 *
 *
 * @Company : SeongbinDB
 * @Author  : Seongbin Ko
 * @Date    : 2021. 2. 22. 오전 10:17:08
 * @Version : 1.0
 */
@RestController
public class CommentApiController {

	@Autowired
	CommentService commentService;


	/**
	 * <pre>
	 * 레시피에 대당하는 전체 댓글조회를 담당한다.
	 * </pre>
	 * @param recipeNo
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/api/recipes/{recipeNo}/comments")
	public List<RecipeComment> getCommentsByRecipeNo(@PathVariable Integer recipeNo, HttpSession session) throws Exception {

		User user = (User) session.getAttribute("USER");
		return commentService.getCommentsByRecipeNo(recipeNo, user);
	}

	/**
	 * <pre>
	 * 댓글등록 기능을 담당한다.
	 * </pre>
	 * @param comment
	 * @throws Exception
	 */
	@PostMapping("/api/recipes/{recipeNo}/comments")
	public void saveComment(RecipeComment comment) throws Exception {
		commentService.saveNewComments(comment);
	}

	/**
	 * <pre>
	 * 댓글삭제 기능을 담당한다.
	 * </pre>
	 * @param commentNo
	 * @throws Exception
	 */
	@DeleteMapping("/api/recipes/{recipeNo}/comments/{commentNo}")
	public void deleteComment(@PathVariable Integer commentNo) throws Exception {
		commentService.deleteComment(commentNo);
	}

	/**
	 * <pre>
	 * 게시글 수정 기능을 담당한다.
	 * </pre>
	 * @param commentNo
	 * @param comment
	 * @throws Exception
	 */
	@PutMapping("/api/recipes/{recipeNo}/comments/{commentNo}")
	public void updateComment(@PathVariable Integer commentNo,
							  @RequestBody String comment) throws Exception {
		commentService.updateComment(commentNo, comment);
	}
}
