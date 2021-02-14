/*비밀번호 유효성검사*/
function password() {
    $("#password").blur(function () {
        /*$("#user_fail").text("");*/
        var password = $('#password').val();

        if (reg_password.test(password)) {
            $("#password_check").text("");
        } else if (password == "") {
            $('#password_check').text('비밀번호를 입력해주세요.');
            $('#password_check').css('color', 'red');
        } else {
            $('#password_check').text("비밀번호는 영어와 숫자 특수문자 8~20자리만 가능합니다.");
            $('#password_check').css('color', 'red')
        }
    });
}

/* 전화번호 유효성검사 */
function phoneNo() {
    $("#phoneNo").blur(function () {
        var phoneNo = $("#phoneNo").val();

        if (reg_phone.test(phoneNo)) {
            $("#phoneNo_check").text("사용가능한 전화번호입니다.");
            $("#phoneNo_check").css("color", "blue");
        } else if (phoneNo == "") {
            $('#phoneNo_check').text('전화번호를 입력해주세요.');
            $('#phoneNo_check').css('color', 'red');
        } else {
            $('#phoneNo_check').text("전화번호를 확인해주세요.");
            $('#phoneNo_check').css('color', 'red');
        }
    });
}

/* 전화번호 MaxLength */
function numberMaxLength(e) {
    if (e.value.length > e.maxLength) {
        e.value = e.value.slice(0, e.maxLength);
    }
}

/* 전화번호 숫자만 허용 */
function onlyNumber() {
    if ((event.keyCode < 48) || (event.keyCode > 57)) {
        event.returnValue = false;
    }
}