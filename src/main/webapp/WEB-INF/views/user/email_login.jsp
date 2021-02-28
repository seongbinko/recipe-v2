<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>레시피</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jdenticon@3.1.0/dist/jdenticon.min.js" async
            integrity="sha384-VngWWnG9GS4jDgsGEUNaoRQtfBGiIKZTiXwm9KpgAeaRn6Y/1tAFiyXqSzqC8Ga/" crossorigin="anonymous">
    </script>
</head>
<body>
<header>
    <%@include file="../common/nav.jsp" %>
</header>
<div class="container">
    <div class="py-5 text-center">
        <h2>패스워드 없이 로그인하기</h2>
    </div>
    <div class="col-sm-3"></div>
    <div class="form-block">
        <form action="/email_login" class="needs-validation col-sm-6" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="email">이메일</label>
                    <input aria-describedby="usernameHelp" class="form-control" id="email" name="email" required maxlength="20"
                           minlength="3" type="text">
                    <small id="usernameHelp" class="form-text text-muted">
                        가입할 때 사용한 이메일을 입력하세요
                    </small>
            </div>
            <div class="form-group">
                <button aria-describedby="submitHelp" class="btn btn-success btn-block"
                        type="submit">로그인 링크 보내기
                </button>
                <small id="submitHelp" class="form-text text-muted">
                    Recipe에 처음 오신거라면 <strong><u><a href="/sign_up">회원가입을 먼저 해주세요</a></u></strong>
                </small>
            </div>
        </form>
    </div>
    <br>
    <div class="col-sm-3">
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                <p><c:out value="${error}"/></p>
            </div>
        </c:if>
        <c:if test="${!empty message}">
            <div class="alert alert-success" role="alert">
                <span><c:out value="${message}"/></span>
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
        </c:if>
    </div>
</div>
<%@include file="../common/footer.html" %>
</body>
</html>