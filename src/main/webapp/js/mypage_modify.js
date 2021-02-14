$(document).ready(function () {
    /*비밀번호 유효성검사 - validation.js*/
    password();
    /*전화번호 유효성검사 - validation.js*/
    phoneNo();

    /*비밀번호 변경 유효성 검사*/
    $("#passwordchange").blur(function () {
        var passwordchange = $('#passwordchange').val();

        if (reg_password.test(passwordchange)) {
            $("#passwordchange_check").text("사용가능한 비밀번호입니다.");
            $("#passwordchange_check").css("color", "blue");
        } else if (passwordchange == "") {
            $('#passwordchange_check').text('비밀번호를 입력해주세요.');
            $('#passwordchange_check').css('color', 'red');
            $("#modify_submit").attr("disabled", true);
        } else {
            $('#passwordchange_check').text("비밀번호는 영어와 숫자 특수문자 8~20자리만 가능합니다.");
            $('#passwordchange_check').css('color', 'red');
            $("#modify_submit").attr("disabled", true);
        }
    });
    /*비밀번호 변경 확인 유효성 검사*/
    $("#passwordchangecheck").blur(function () {
        var passwordchange = $("#passwordchange").val();
        var passwordchangecheck = $("#passwordchangecheck").val();

        if (passwordchangecheck == "") {
            $('#passwordchangecheck_check').text('비밀번호확인을 입력해주세요.');
            $('#passwordchangecheck_check').css('color', 'red');
            $("#modify_submit").attr("disabled", true);
        } else if (passwordchange == passwordchangecheck) {
            $("#passwordchangecheck_check").text("비밀번호가 일치합니다.");
            $("#passwordchangecheck_check").css("color", "blue");
            $("#modify_submit").attr("disabled", false);
        } else {
            $('#passwordchangecheck_check').text("비밀번호가 일치하지 않습니다.");
            $('#passwordchangecheck_check').css('color', 'red');
            $("#modify_submit").attr("disabled", true);
        }
    });
});