'use strict';
let cropper = '';
let $confirmBtn = $('#confirm-button');
let $resetBtn = $("#reset-button");
let $cutBtn = $('#cut-button');
let $newProfileImage = $('#new-profile-image');
let $currentProfileImage = $("#current-profile-image");
let $resultImage = $('#cropped-new-profile-image');
let $profileImage = $('#profileImage');
let $profileImageFIle = $('#profile-image-file');

// 초기 버튼과 새로운 프로필 이미지 숨기기
$newProfileImage.hide();
$cutBtn.hide();
$resetBtn.hide();
$confirmBtn.hide();

$("#profile-image-file").change(function (e) {      // 프로필 사진이 바뀔때
    if (e.target.files.length === 1) {
        const reader = new FileReader();
        reader.onload = e => {
            if (e.target.result) {                           // 파일을 가져왔을 경우
                // 이미지태그 만들기
                let img = document.createElement("img");
                img.id = 'new-profile';
                img.src = e.target.result;
                img.width = 250;

                $newProfileImage.html(img);     // newProfileImage영역에 위에서 만든 이미지 태그 삽입
                $newProfileImage.show();
                $currentProfileImage.hide();

                // Cropper 적용
                let $newImage = $(img);
                $newImage.cropper({aspectRatio: 1});
                cropper = $newImage.data('cropper');

                $cutBtn.show();
                $confirmBtn.hide();
                $resetBtn.show();
            }
        };

        reader.readAsDataURL(e.target.files[0]);
    }
});

$resetBtn.click(function () {
    $currentProfileImage.show();
    $newProfileImage.hide();
    $resultImage.hide();
    $resetBtn.hide();
    $cutBtn.hide();
    $confirmBtn.hide();
    $profileImage.val('');
    $profileImageFIle.val('');
});

$cutBtn.click(function () {
    let dataUrl = cropper.getCroppedCanvas().toDataURL();
    let newImage = document.createElement("img");
    newImage.id = "cropped-new-profile-image";
    newImage.src = dataUrl;
    newImage.width = 125;
    $resultImage.html(newImage);
    $resultImage.show();
    $confirmBtn.show();

    $confirmBtn.click(function () {
        $newProfileImage.html(newImage);
        $cutBtn.hide();
        $confirmBtn.hide();
        $profileImage.val(dataUrl);
    });
});

let tabInfo = $("#tab-info").val();
if(tabInfo === "profile") {
    $("#profile-li").attr("class", "active");
    $("#password-li").attr("class", "");
    $("#account-li").attr("class", "");

    $("#profile-tab").attr("class", "tab-pane fade active in");
    $("#password-tab").attr("class", "tab-pane fade");
    $("#account-tab").attr("class", "tab-pane fade");
} else if(tabInfo === "password") {
    $("#profile-li").attr("class", "");
    $("#password-li").attr("class", "active");
    $("#account-li").attr("class", "");

    $("#profile-tab").attr("class", "tab-pane fade");
    $("#password-tab").attr("class", "tab-pane fade active in");
    $("#account-tab").attr("class", "tab-pane fade");
} else if(tabInfo === "account") {
    $("#profile-li").attr("class", "");
    $("#password-li").attr("class", "");
    $("#account-li").attr("class", "active");

    $("#profile-tab").attr("class", "tab-pane fade");
    $("#password-tab").attr("class", "tab-pane fade");
    $("#account-tab").attr("class", "tab-pane fade active in");
}

let loginByEmailUrl = document.referrer;

if(loginByEmailUrl.includes("login-by-email")) {
    $("#profile-li").attr("class", "");
    $("#password-li").attr("class", "active");
    $("#account-li").attr("class", "");

    $("#profile-tab").attr("class", "tab-pane fade");
    $("#password-tab").attr("class", "tab-pane fade active in");
    $("#account-tab").attr("class", "tab-pane fade");
}