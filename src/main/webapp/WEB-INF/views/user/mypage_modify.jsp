<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원정보수정</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/mypage_modify.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="/js/mypage_modify.js"></script>
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
                    <li><a href="mypage_modify"><strong>회원정보수정</strong></a></li>
                    <li><a href="mypage_scrap">내스크랩</a></li>
                </ul>
                <br>
            </aside>
            <article class="col-sm-9">

                <h4>회원정보수정</h4>
                <hr>
                <div class="row">
                    <span id="Essential"><span class="glyphicon glyphicon-asterisk">필수입력</span></span>
                    <form class="form-horizontal" onclick="submitModifyForm(form);" action="modifyrun" method="POST">
                        <div class="form-group">
                            <label class="control-label col-sm-3">아이디</label>
                            <div class="col-sm-5" style="margin-top: 5px;">
                                <c:if test="${not empty USER}">
                                    ${USER.id}
                                    <input type="hidden" name="id" value="${USER.id}">
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3 glyphicon glyphicon-asterisk"
                                   for="password">현재비밀번호</label>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" id="password" name="nowpw"
                                       placeholder="현재 비밀번호 입력" maxlength="20" autofocus=autofocus>
                                <div class="check_font" id="password_check"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="passwordchange">변경할 비밀번호</label>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" id="passwordchange" name="passwordchange"
                                       placeholder="8~20자의 영문+숫자+특수문자" maxlength="20">
                                <div class="check_font" id="passwordchange_check"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="passwordchangecheck">변경할 비밀번호 확인</label>
                            <div class="col-sm-5">
                                <input type="password" class="form-control" id="passwordchangecheck" name="password"
                                       placeholder="변경할 비밀번호 확인" maxlength="20">
                                <div class="check_font" id="passwordchangecheck_check"></div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3">닉네임</label>
                            <div class="col-sm-5" style="margin-top: 5px;">
                                <c:if test="${not empty USER}">
                                    ${USER.nickname}
                                    <input type="hidden" name="nickname" value="${USER.nickname}">
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="phoneNo">전화번호</label>
                            <div class="col-sm-5">
                                <input type="number" class="form-control" id="phoneNo" name="phoneNo" maxlength="11"
                                       placeholder="연락처(-없이) 입력" onkeypress="onlyNumber();"
                                       oninput="numberMaxLength(this);"
                                       value=<c:if test="${not empty USER}">
                                    ${USER.phoneNo}</c:if>>
                                <div class="check_font" id="phoneNo_check"></div>
                                <br>
                                <c:if test="${param.fail == 'invalidUser'}">
                                    <div id="user_fail" style="color: red; font-weight: bold;">올바르지 않은 입력이거나 비밀번호를
                                        확인해주세요.
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-8 col-sm-2">
                                <button type="submit" id="modify_submit" class="btn btn-success">수정</button>
                            </div>
                        </div>
                    </form>
                </div>
            </article>
        </div>
    </div>
</section>
<br>
<br>
</body>
<script src="/js/reg.js"></script>
<script src="/js/validation.js"></script>
<script src="/js/mypage_modify.js"></script>
</html>