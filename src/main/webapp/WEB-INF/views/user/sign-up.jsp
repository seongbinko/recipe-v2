<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <meta name="_csrf" content="${_csrf.token}">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
        <h2>회원 가입</h2>
    </div>
    <div class="col-sm-3"></div>
    <div class="form-block">
        <form action="sign-up" class="needs-validation col-sm-6" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="nickname">닉네임</label>
                <c:if test="${param.nickname eq null}">
                    <input aria-describedby="nicknameHelp" class="form-control" id="nickname" name="nickname" required maxlength="20"
                           minlength="3" placeholder="recipe" type="text">
                </c:if>
                <c:if test="${param.nickname ne null}">
                    <input aria-describedby="nicknameHelp" class="form-control" id="nickname" name="nickname" required maxlength="20"
                           minlength="3" placeholder="recipe" type="text" value="${param.nickname}">
                </c:if>
                <small class="form-text text-muted" id="nicknameHelp">
                    공백없이 문자와 숫자로만 3자 이상 20자 이내로 입력하세요. 가입후에 변경할 수 있습니다.<br/>
                </small>
                <c:if test="${map['nickname'] ne null}">
                <small class="form-text text-danger">Nickname
                    Error: <c:out value="${map['nickname']}"/> </small>
                </c:if>
            </div>

            <div class="form-group">
                <label for="email">이메일</label>
                <c:if test="${param.email eq null}">
                    <input aria-describedby="emailHelp" type="email" class="form-control" id="email" name="email" required placeholder="your@email.com">
                </c:if>
                <c:if test="${param.email ne null}">
                    <input aria-describedby="emailHelp" type="email" class="form-control" id="email" name="email" required placeholder="your@email.com" value="${param.email}">
                </c:if>
                <small class="form-text text-muted" id="emailHelp">
                    Recipe는 사용자의 이메일을 공개하지 않습니다.<br/>
                </small>
                <c:if test="${map['email'] ne null}">
                    <small class="form-text text-danger">Email
                        Error: <c:out value="${map['email']}"/></small>
                </c:if>
            </div>

            <div class="form-group">
                <label for="password">패스워드</label>
                <c:if test="${param.password eq null}">
                    <input aria-describedby="passwordHelp" class="form-control" id="password" name="password" required maxlength="50"
                           minlength="8" type="password">
                </c:if>
                <c:if test="${param.password ne null}">
                    <input aria-describedby="passwordHelp" class="form-control" id="password" name="password" required maxlength="50"
                           minlength="8" type="password" value="${param.password}">
                </c:if>
                <small class="form-text text-muted" id="passwordHelp">
                    8자 이상 50자 이내로 입력하세요. 영문자, 숫자, 특수기호를 사용할 수 있으며 공백은 사용할 수 없습니다.<br/>
                </small>
                <c:if test="${map['password'] ne null}">
                    <small class="form-text text-danger">Password
                        Error: <c:out value="${map['password']}"/></small>
                </c:if>
            </div>

            <div class="form-group">
                <button aria-describedby="submitHelp" class="btn btn-success btn-block"
                        type="submit">가입하기
                </button>
                <small class="form-text text-muted" id="submitHelp">
                    <a href="#">약관</a>에 동의하시면 가입하기 버튼을 클릭하세요.
                </small>
            </div>
        </form>
    </div>
    <div class="col-sm-3"></div>
</div>
</body>
<script src="/js/sign-up.js"></script>
<script>
</script>
</html>