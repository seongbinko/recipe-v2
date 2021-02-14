$(document).ready(function () {

	// 스크랩여부를 확인해서 스크랩, 스크랩취소 버튼 show 할지 hide 할지 결정
	var scrapStatus = $("#scrap-amount").data("scrap-status");
	if (scrapStatus == false) {
		$("#cancel-scrap").hide();

	} else {
		$("#do-scrap").hide();
	}
	getComments();
});

// 레시피 삭제 버튼
$("#delete-btn").click(function () {
	var recipeNo = $("#recipeNo").data("recipeno");
	if (confirm("정말 삭제하시겠습니까?") == true) {
		$.ajax({
			type: "DELETE",
			url: "/api/recipes/" + recipeNo,
			success: function () {
				alert("삭제 되었습니다.");
				location.href = '/recipes';
			}
		});
	} else {

	}
})

// 스크랩 버튼
$("#do-scrap").click(function () {
	var recipeNo = $("#recipeNo").data("recipeno");
	$.ajax({
		type: "POST",
		url: "/api/recipes/scraps/" + recipeNo,
		dataType: "json",
		success: function (result) {
			$("#do-scrap").hide();
			$("#cancel-scrap").show();
			$('#scrap-amount').text(result);
			alert("스크랩 하였습니다. 마이페이지에서 확인 가능합니다.");
		},
		error: function () {
			alert("알 수 없는 오류가 발생하였습니다.");
		}
	})
});

// 스크랩 취소
$("#cancel-scrap").on('click', function () {
	var recipeNo = $("#recipeNo").data("recipeno");
	$.ajax({
		url: "/api/recipes/scraps/" + recipeNo,
		method: "DELETE",
		dataType: "json",
		success: function (result) {
			$("#cancel-scrap").hide();
			$("#do-scrap").show();
			$('#scrap-amount').text(result);
			alert("스크랩이 취소되었습니다.");
		}
	});
});

/**
 * 레시피 댓글 스크립트
 */

/*
 * 레시피에 해당하는 댓글들 조회
 */
function getComments() {
	var recipeNo = $("#recipeNo").data("recipeno");

	$.ajax({
		type: "GET",
		url: "/api/recipes/" + recipeNo + "/comments",
		dataType: "json",
		contentType: "application/json; charset=utf-8",
		success: function (result) {
			var content = `<div class="comment-option">
								<h3 id="comment-title">댓글<span class="badge">${result.length}</span></h3>
							</div>`;
			$.each(result, function (index, item) {

				// level이 1이 아닌것 즉 하위에 속한것
				if (item.commentLevel != 1) {
					//  계층마다 마진으로 차등을 댓글의 차등을 준다.
					var marginleft = (item.commentLevel - 1) * 50;
					content += `<ul class="list-group" data-comment-writer="${item.commentWriter}" data-comment-level="${item.commentLevel}" data-comment-no ="${item.commentNo}" data-parent-no="${item.commentParentNo}" data-comment="${item.commentContent}"  data-fulldate="${item.fullDate}" style="margin-left: ${marginleft}px;">`
				} else {
					// 부모 계층인 것
					content += `<ul class="list-group" data-comment-writer="${item.commentWriter}" data-comment-level="${item.commentLevel}" data-comment-no ="${item.commentNo}" data-comment="${item.commentContent}" data-fulldate="${item.fullDate}">`
				}
				content += `<li class="list-group-item">
								<p><strong>${item.commentWriter}</strong></p>
								<p>${item.commentContent}</p>`;

				// 삭제 댓글인지 아닌지를 확인하기 위함
				if (item.commentStatus != "N") {
					content += `<p><span>최종 수정일: ${item.fullDate} </span><span><button type="button" class="btn-reply">답글쓰기</button>`
				}

				// 댓글작성자이면 수정 삭제가 가능하게 하기 위함
				if (item.commentStatus != "N" && item.role == "w") {
					content += `<button class="btn-update">수정</button><button class="btn-delete">삭제</button>`;
				}
				content += `</span></p></li></ul>`;

			})
			$("#comment-show").empty().append(content);
			// 댓글 등록 활성화
			saveComment();
			// 답변하기 Add 창 활성화
			attachReplyDiv();
			// 자신의 댓글 수정버튼 누를시 수정창으로 바뀌는 것 활성화
			changeCommentDiv();
			// 자신 댓글 삭제 기능 활성화
			deleteComment();
		}
	})
}

/*
 * 댓글 삭제 
 */
function deleteComment() {
	$(".btn-delete").off().on('click', function () {

		var commentNo = $(this).closest("ul").data("comment-no")
		var recipeNo = $("#recipeNo").data("recipeno");
		$.ajax({
			method: "DELETE",
			url: "/api/recipes/" + recipeNo + "/comments/" + commentNo
		}).done(function () {
			alert("삭제가 완료되었습니다.");
			getComments();
		}).fail(function () {
			alert("알수 없는 오류가 발생하였습니다.")
			location.href = "/recipes/" + recipeNo;
		})
	})
}

/*
 * 새 댓글을 등록한다.
 */
function saveComment() {
	var recipeNo = $("#recipeNo").data("recipeno");
	var nickName = $("#nickname").data("user-nickname");

	$(".attach-comment").off().on('click', function () {
		var data = {}

		var content = $(this).closest("#comment-writer").find("textarea").val();

		if (content == null) {

			content = $(this).closest("#comment-reply").find("textarea").val();
			data.commentParentNo = $(this).closest("ul").data("comment-no");
			$(this).closest("#comment-reply").find("textarea").val("");

		} else {
			$(this).closest("#comment-writer").find("textarea").val("");
		}
		if (content.trim().length < 15) {
			alert("현재 타이핑수: " + content.trim().length + " 최소 타이핑 수는 15 이상입니다.");
			return;
		}
		data.commentContent = content;
		data.recipeNo = recipeNo;
		data.commentWriter = nickName;

		$.post("/api/recipes/" + recipeNo + "/comments", data, function () {
			getComments();
			alert("댓글이 등록되었습니다.");
		}).fail(function () {
			alert("알수없는 오류가 발생하였습니다");
		})
	})
}

/*
 * 답변쓰기 창 붙여주는 화면
 */
function attachReplyDiv() {
	$(".btn-reply").off().on('click', function () {
		$("#comment-reply").remove();

		var nickName = $("#nickname").data("user-nickname");
		var selector = $(this).closest("ul")
		var test = $(this).closest("ul").data("comment-writer")
		var content = `<div id="comment-reply">
							<h3>${nickName}<button class="attach-comment">등록</button></h3>
							<textarea rows="10" cols="124" placeholder="댓글을 남겨주세요" maxlength="200">To:${test}\n</textarea>
						</div>`
		$(selector).append(content);
		saveComment();
	})
}

/*
 * 다른 창 수정버튼 클릭시 예외처리 및, 취소버튼 클릭시 원래의 창으로 되돌아 가는 부분
 */
function changeCommentDiv() {
	$(".btn-update").off().on('click', function () {

		if ($("#comment-update").text() != "") {
			alert("한번에 하나의 수정만 가능합니다.")
			return;

		}
		var commentNo = $(this).closest("ul").data("comment-no");
		var comment = $(this).closest("ul").data("comment");
		var writer = $("#nickname").data("user-nickname");
		var selector = $(this).closest("ul")
		currentHtml = selector;

		var content = `<div id="comment-update" data-commentno="${commentNo}" data-comment="${comment}" data-writer="${writer}">
							<h3>${writer}<button class="update-comment">수정</button><button class="btn-comment-cancle">취소</button></h3>
							<textarea rows="10" cols="124" placeholder="댓글을 남겨주세요" maxlength="200">${comment}</textarea>
						</div>`
		$(selector).empty();
		$(selector).append(content);
		// 댓글 업데이트 기능 활성화
		updateComment();
		// 댓글 수정취소 기능 활성화
		cancelComment();
	})
}

/*
 * 댓글 수정취소 기능
 */
function cancelComment() {
	$(".btn-comment-cancle").on('click', function () {
		var selector = $(this).closest("ul");
		var content = `<li id class="list-group-item">
							<p><stong>${selector.data("comment-writer")}</strong></p>
							<p>${selector.data("comment")}</p>
							<p><span>최종 수정일: ${selector.data("fulldate")}</span>
							<span><button type="button" class="btn-reply">답글쓰기</button></span>
							<button class="btn-update">수정</button><button class="btn-delete">삭제</button></p>
						</li>`
		$("#comment-update").remove();
		$(selector).append(content);
		// 답글달기 기능 활성화
		attachReplyDiv();
		//다른 창 수정버튼 클릭시 예외처리 및, 취소버튼 클릭시 원래의 창으로 되돌아 가는 부분
		changeCommentDiv();
	})
}

/*
 * 댓글 수정 api
 */
function updateComment() {
	$(".update-comment").on('click', function () {
		var recipeNo = $("#recipeNo").data("recipeno");
		var commentNo = $("#comment-update").data("commentno");
		var comment = $("#comment-update textarea").val();
		if (comment.trim().length < 15) {
			alert("현재 타이핑수: " + comment.trim().length + " 최소 타이핑 수는 15 이상입니다.");
			return;
		}

		$.ajax({
			type: "PUT",
			url: "/api/recipes/" + recipeNo + "/comments/" + commentNo,
			data: comment,
			contentType: "application/json; charset=utf-8"
		}).done(function () {
			getComments();
			alert("수정되었습니다.")
		}).fail(function () {
			alert("예기치 않은 오류가 발생하였습니다..")
		})
	})
}


