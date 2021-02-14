<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>로그인</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/login.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<header>
    <%@include file="../common/navheader.jsp" %>
</header>
<section class="conteiner">
    <form class="form-horizontal col-sm-11" method="POST" action="loginrun">
        <div class="form-group">
            <label class="control-label col-sm-5" for="id">아이디</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="id" name="id" placeholder="아이디" maxlength="12"
                       autofocus=autofocus>
                <div class="check_font" id="id_check"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-5" for="password">비밀번호</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호"
                       maxlength="20">
                <div class="check_font" id="password_check"></div>
                <br>
                <c:if test="${param.fail == 'invalidUser'}">
                    <div id="user_fail" style="color: red; font-weight: bold;">
                        아이디 또는 비밀번호가 올바르지 않습니다.
                    </div>
                </c:if>
                <div class="join">
                    <a href=join>회원가입</a>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-6">
                <button type="submit" value="로그인" id="login_submit" class="btn btn-success btn-lg col-sm-4">로그인</button>
            </div>
        </div>
    </form>
</section>
</body>
</html>