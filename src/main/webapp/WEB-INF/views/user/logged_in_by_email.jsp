<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>레시피</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <meta name="_csrf" content="${_csrf.token}">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jdenticon@3.1.0/dist/jdenticon.min.js" async
            integrity="sha384-VngWWnG9GS4jDgsGEUNaoRQtfBGiIKZTiXwm9KpgAeaRn6Y/1tAFiyXqSzqC8Ga/" crossorigin="anonymous">
    </script>
    <style>
        .container {
            max-width: 100%;
        }
    </style>
</head>
<body>
<header>
    <%@include file="../common/nav.jsp" %>
</header>
<div class="container">
    <c:if test="${error ne null}">
        <!-- 에러 페이지를 호출하도록-->
        <div class="py-5 text-center">
            <p class="lead">Recipe 이메일 로그인</p>
            <div class="alert alert-danger" role="alert">${error}</div>
            로그인 할 수 없습니다.
        </div>
    </c:if>
    <c:if test="${error eq null}">
        <div class="py-5 text-center">
            <p class="lead">Recipe 이메일 로그인</p>
            <h2>이메일로 로그인 했습니다. <u><a href="/user/update/" style="color: blue">패스워드를 변경</a></u>하세요</h2>
        </div>
    </c:if>
</div>
<%@include file="../common/footer.html" %>
</body>
</html>
