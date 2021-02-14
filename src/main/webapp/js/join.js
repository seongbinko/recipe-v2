$(document).ready(function () {
    /*비밀번호 유효성검사 - validation.js*/
    password();
    /*전화번호 유효성검사 - validation.js*/
    phoneNo();
    /* 비밀번호확인 유효성검사 */
    $("#passwordcheck").blur(function () {
        var password = $("#password").val();
        var passwordcheck = $("#passwordcheck").val();

        if (passwordcheck == "") {
            $('#passwordcheck_check').text('비밀번호확인을 입력해주세요.');
            $('#passwordcheck_check').css('color', 'red');
        } else if (password == passwordcheck) {
            $("#passwordcheck_check").text("비밀번호가 일치합니다.");
            $("#passwordcheck_check").css("color", "blue");
        } else {
            $('#passwordcheck_check').text("비밀번호가 일치하지 않습니다.");
            $('#passwordcheck_check').css('color', 'red');
        }
    });

});
