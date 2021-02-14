<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>회원가입</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/join.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<header>
    <%@include file="../common/navheader.jsp" %>
</header>
<section class="conteiner">
    <div id="Essential">
        <span class="glyphicon glyphicon-asterisk">필수입력</span>
    </div>
    <form class="form-horizontal col-sm-11" method="POST" action="joinrun">
        <div class="form-group">
            <label class="control-label col-sm-5 col-sm-5 glyphicon glyphicon-asterisk" for="id">아이디</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="id" name="id" placeholder="5~12자의 영문 소문자+숫자" maxlength="12"
                       autofocus=autofocus>
                <div class="check_font" id="id_check"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-5 col-sm-5 glyphicon glyphicon-asterisk" for="password">비밀번호</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password" name="password"
                       placeholder="8~20자의 영문+숫자+특수문자" maxlength="20">
                <div class="check_font" id="password_check"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-5 glyphicon glyphicon-asterisk" for="passwordcheck">비밀번호확인</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="passwordcheck" name="passwordcheck"
                       placeholder="비밀번호 확인" maxlength="20">
                <div class="check_font" id="passwordcheck_check"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-5 glyphicon glyphicon-asterisk" for="nickname">닉네임</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="nickname" name="nickname" placeholder="3~10자의 한글+영문+숫자"
                       maxlength="10">
                <div class="check_font" id="nickname_check"></div>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-sm-5" for="phoneNo">전화번호</label>
            <div class="col-sm-4">
                <input type="number" class="form-control" id="phoneNo" name="phoneNo" maxlength="11"
                       placeholder="연락처(-없이) 입력" onkeypress="onlyNumber();" oninput="numberMaxLength(this);">
                <div class="check_font" id="phoneNo_check"></div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-6">
                <button type="submit" value="회원가입" id="join_submit" class="btn btn-success btn-lg col-sm-4">회원가입
                </button>
            </div>
        </div>
    </form>
</section>
</body>
<script>
    $("#join_submit").attr("disabled", true)
    /* 아이디 중복체크, 유효성검사 */
    $("#id").blur(function () {
        var id = $('#id').val();

        $.ajax({ //ajax로 중복된 아이디인지 체크
            url: '${pageContext.request.contextPath}/nicknamecheck?nickname=/idcheck?id=' + id,
            type: 'get',
            success: function (data) {
                console.log("1 = 중복o / 0 = 중복x : " + data);
                if (data == 1) {
                    $("#id_check").text("사용중인 아이디입니다.");
                    $("#id_check").css("color", "red");
                } else {
                    if (reg_id.test(id)) {
                        $("#id_check").text("사용가능한 아이디입니다.");
                        $("#id_check").css("color", "blue");
                    } else if (id == "") {
                        $('#id_check').text('아이디를 입력해주세요.');
                        $('#id_check').css('color', 'red');
                    } else {
                        $('#id_check').text("아이디는 소문자와 숫자 5~12자리만 가능합니다.");
                        $('#id_check').css('color', 'red');
                    }
                }
            },
            error: function () {
                console.log("실패");
            }
        });
    });
    /*닉네임 중복체크, 유효성검사 */
    $("#nickname").blur(function () {
        var nickname = $('#nickname').val();
        $.ajax({ //ajax로 중복된 닉네임인지 체크
            url: '${pageContext.request.contextPath}/nicknamecheck?nickname=' + nickname,
            type: 'get',
            success: function (data) {
                console.log("1 = 중복o / 0 = 중복x : " + data);
                if (data == 1) {
                    $("#nickname_check").text("사용중인 닉네임입니다.");
                    $("#nickname_check").css("color", "red");
                    $("#join_submit").attr("disabled", true)
                } else {
                    if (reg_nickname.test(nickname)) {
                        $("#nickname_check").text("사용가능한 닉네임입니다.");
                        $("#nickname_check").css("color", "blue");
                        $("#join_submit").attr("disabled", false)
                    } else if (nickname == "") {
                        $('#nickname_check').text('닉네임을 입력해주세요.');
                        $('#nickname_check').css('color', 'red');
                        $("#join_submit").attr("disabled", true)
                    } else {
                        $('#nickname_check').text("닉네임은 소문자와 숫자 3~10자리만 가능합니다.");
                        $('#nickname_check').css('color', 'red');
                        $("#join_submit").attr("disabled", true)
                    }
                }
            },
            error: function () {
                console.log("실패");
            }
        });
    });
</script>
<script src="/js/reg.js"></script>
<script src="/js/validation.js"></script>
<script src="/js/join.js"></script>
</html>