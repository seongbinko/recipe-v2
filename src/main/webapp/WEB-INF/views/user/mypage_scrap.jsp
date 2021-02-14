<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>내 스크랩</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<header>
    <%@include file="../common/navheader.jsp" %>
</header>
<section class="container">
    <div class="page-header">
        <h2>마이페이지</h2>
    </div>
    <div class="container-fluid">
        <div class="row content">
            <aside class="col-sm-3 sidenav">
                <ul class="nav nav-pills nav-stacked">
                    <li><a href="mypage_modify">회원정보수정</a></li>
                    <li><a href="mypage_scrap"><strong>내스크랩</strong></a></li>
                </ul>
                <br>
            </aside>
            <article class="col-sm-9">
                <h4>내 스크랩</h4>
                <hr>
                <div class="form-group">
                    <div class="col-sm-offset-11">
                        <button type="button" id="btn-aaa" class="btn btn-success" onclick="cb()">삭제</button>
                    </div>
                </div>
                <div class="container-fluid bg-3 text-center">
                    <div class="row">
                        <c:forEach var="reScrap" items="${reScrap}" varStatus="status"> <!--이걸로쓰겠다/가져온애/포문으로치면i -->
                            <%-- <c:if test="${status.current%4==1}"> 가져온값 4개로짜르는 기능(연구)
                            <br> 일때 br로 내려줘라
                            </c:if> --%>
                            <div class="col-sm-3">
                                <img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive"
                                     onclick="location.href='/recipes/${reScrap.recipeNo}';">
                                <div>${reScrap.recipename}</div>
                                <div>by ${reScrap.userId}</div>
                                <div>${reScrap.recipeScrapDate}</div>
                                <input type="checkbox" name="cb" value="${reScrap.recipeNo}">
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <br>
                <ul class="pagination">
                    <li><a href="#">1</a></li>
                    <li class="active"><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                </ul>
                <br>
                <br>
            </article>
        </div>
    </div>
</section>
<script>

    function cb() {
        var cblist = [];
        $('input:checkbox[name="cb"]').each(function () {

            if (this.checked) {//checked 처리된 항목의 값 / 그녀석이 체크가 되어있으면
                cblist.push(this.value); /* 그녀석의 값을 list담아라 */
                alert(this.value);	/* 체크한값(리스트)를 자바로 보내서 delete실행 */
            }

        });


    }

</script>
</body>
</html>