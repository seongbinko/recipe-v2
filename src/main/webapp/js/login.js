$(document).ready(function () {
    /*비밀번호 유효성검사 - validation.js*/
    password();
    /*아이디 유효성검사*/
    $("#id").blur(function () {
        $("#user_fail").text("");
        var id = $('#id').val();
        if (reg_id.test(id)) {
            $("#id_check").text("");
            $("#id_check").css("color", "blue");
        } else if (id == "") {
            $('#id_check').text('아이디를 입력해주세요.');
            $('#id_check').css('color', 'red');
        } else {
            $('#id_check').text("아이디는 소문자와 숫자 5~12자리만 가능합니다.");
            $('#id_check').css('color', 'red');
        }
    });
});