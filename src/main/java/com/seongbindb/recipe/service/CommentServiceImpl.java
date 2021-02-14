/**
 *
 */
package com.seongbindb.recipe.service;

import com.seongbindb.recipe.mapper.CommentMapper;
import com.seongbindb.recipe.vo.RecipeComment;
import com.seongbindb.recipe.vo.User;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
 * @Date    : 2021. 2. 22. 오전 10:30:38
 * @Version : 1.0
 */
@Service
@MapperScan(basePackages = "com.seongbindb.recipe.mapper")
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentMapper commentMapper;

	@Override
	public List<RecipeComment> getCommentsByRecipeNo(Integer recipeNo, User user) {
		// 레시피번호에 해당하는 모든 댓글 조회
		List<RecipeComment> comments = commentMapper.selectCommentsByRecipeNo(recipeNo);

		//댓글을 작성한 사람이라면 수정 삭제가 가능해야하기 때문에 그 부분을 검증한다.
		for (RecipeComment comment : comments) {
			if (comment.getCommentWriter() != null && comment.getCommentWriter().equals(user.getNickname())) {
				comment.setRole("w");
			}
			comment.setFullDate(comment.getCommentModDate());
		}
		return comments;
	}

	@Override
	public void saveNewComments(RecipeComment comment) {
		commentMapper.insertNewComment(comment);
	}

	@Override
	public void deleteComment(Integer commentNo) {
		// 삭제할 댓글에 관련있는 댓글들으 조회한다.
		int count = commentMapper.getCountRelatedComments(commentNo);
		// 없으면 삭제
		if (count == 0) {
			commentMapper.deleteComment(commentNo);
			// 있으면 다른처리
		} else {
			RecipeComment comment = commentMapper.selectCommentByNo(commentNo);
			comment.setCommentContent("원 댓글이 삭제된 게시물입니다.");
			comment.setCommentWriter(" ");
			commentMapper.updateDisableComment(comment);
		}
	}

	@Override
	public void updateComment(Integer commentNo, String comment) {
		RecipeComment recipeComment = new RecipeComment();
		recipeComment.setCommentContent(comment);
		recipeComment.setCommentNo(commentNo);

		commentMapper.updateComment(recipeComment);
	}
}
