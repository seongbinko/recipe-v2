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
    <div class="py-5 text-center">
        <p class="lead">레시피 이메일 확인</p>
        <c:if test="${error ne null}">
            <div class="alert alert-danger" role="alert">
                이메일 확인 링크가 정확하지 않습니다.
            </div>
        </c:if>
        <c:if test="${error eq null}">
            <h2>이메일을 확인했습니다. <span><c:out value="${numberOfUser}"/></span>번째 회원,
                <span><c:out value="${nickname}"/></span>님 가입을 축하합니다.
            </h2>
            <small class="text-info">이제부터 가입할 때 사용한 이메일 또는 닉네임과 패스워드로 로그인 할 수 있습니다.</small>
        </c:if>
    </div>
</div>
</body>
</html>
