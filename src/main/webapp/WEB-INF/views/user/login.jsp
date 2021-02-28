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
        <h2>로그인</h2>
    </div>
    <div class="col-sm-3"></div>
    <div class="form-block">
        <form action="login" class="needs-validation col-sm-6" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="username">닉네임 또는 이메일</label>
                    <input aria-describedby="usernameHelp" class="form-control" id="username" name="username" required maxlength="20"
                           minlength="3" type="text">
            </div>
            <div class="form-group">
                <label for="password">패스워드</label>
                    <input aria-describedby="passwordHelp" class="form-control" id="password" name="password" required maxlength="50"
                           minlength="8" type="password">
                <small id="passwordHelp" class="form-text text-muted">
                    패스워드가 기억나지 않는다면 <strong><u><a href="/email_login">패스워드 없이 로그인하기</a></u></strong>
                </small>
            </div>
            <div class="form-group form-check">
                <input type="checkbox" class="form-check-input" id="rememberMe" name="remember-me" checked/>
                <label class="form-check-lable" for="rememberMe" aria-describedby="rememberMeHelp">로그인 유지</label>
            </div>

            <div class="form-group">
                <button aria-describedby="submitHelp" class="btn btn-success btn-block"
                        type="submit">로그인
                </button>
                <small id="submitHelp" class="form-text text-muted">
                    Recipe에 처음 오신거라면 <strong><u><a href="/sign_up">회원가입을 먼저 해주세요</a></u></strong>
                </small>
            </div>
        </form>
    </div>
    <br>
    <div class="col-sm-3">
        <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
            <div class="alert alert-danger" role="alert">
                <p>이메일(또는 닉네임)과 패스워드가 <br>일치하지 않습니다.</p>
                <p>또는 확인되지 않는 이메일을 사용했습니다. 이메일을 확인해 주세요.</p>
                <p>
                    확인 후 다시 입력하거나,<br><strong><u><a href="/find-password">패스워드 찾기</a></u></strong>를 이용하세요
                </p>
            </div>
            <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
        </c:if>
    </div>
</div>
<%@include file="../common/footer.html" %>
</body>
</html>