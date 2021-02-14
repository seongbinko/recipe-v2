/**
 * 레시피 등록 스크립트
 * https://mobicon.tistory.com/189 this의 의미
 */
function checkSave() {
    if (confirm("등록 하시겠습니까?") == true) {
        alert("등록 되었습니다.")
        return true;
    }
    return false;
}

function checkUpdate() {
    if (confirm("수정 하시겠습니까?") == true) {
        alert("수정 되었습니다.")
        return true;
    }
    return false;
}

var recipe = {
    init: function () {
        // init 이라는 곳 안에서만 scope를 설정해서 사용을 하겠다.
        var _this = this;

        $('#btn-add').off().on('click', function (e) {
            _this.add();
            e.preventDefault();
        });

        $('#btn-cancle').off().on('click', function (e) {
            _this.cancle();
            e.preventDefault();
        });


        $(":input[name='img']").off().on('change', function (e) {
            if ($(this).val() != "") {
                var ext = $(this).val().split('.').pop().toLowerCase();
                // 해당 확장자명이 아니면 return;
                if ($.inArray(ext, ['png', 'jpg', 'jpeg']) == -1) {
                    alert("png,jpg 파일만 업로드 해주세요");
                    $(this).val("");
                    return;
                }

                var fileSize = this.files[0].size;
                var maxSize = 5 * 1024 * 1024;
                if (fileSize > maxSize) {
                    alert("파일업로드는 용량이 5MB 이하만 가능합니다.");
                    $(this).val("");
                    return;
                }
            }
            var sel_file;
            var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);
            var _this = $(this).closest('.preview-img').find('[name^="pre_img_"]');

            filesArr.forEach(function (f) {
                sel_file = f;

                // 비동기적으로 파일을 읽어올 수 있다
                var reader = new FileReader();

                //읽기과정이 성공적으로 마무리되었을 때 실행된다
                //https://www.bsidesoft.com/323
                reader.onload = function (e) {
                    _this.attr("src", e.target.result);
                }
                // 셋팅을 다해놓고 url로 파일을 읽어오겠따?
                reader.readAsDataURL(f);
            });
        });

    },
    add: function () {


        var stepNumber = $('[id^="step_"]:last').index() + 2;

        var row = ` 
			<div id='step_${stepNumber}' class='preview-img'>
			<div class='form-group'>
			<label class='control-label col-sm-2'><span>Step ${stepNumber}</span></label>
			<input type='hidden' name='stepIndex' value='${stepNumber - 1}'>
			<input type='hidden' name='recipeDetailNo'>
			<div class='col-sm-3'>
			<img name='pre_img_${stepNumber}'alt='cooking' src='/images/logo/headerlogo01.png' width='180px;'>
			</div>
			<div class='form-group'>
			<textarea rows='8' cols='70' style='resize: none;' placeholder='요리방법을 작성해주세요' name='content' required></textarea>
			</div>
			<div class='form-group'>
			<label class='control-label col-sm-2'><span id='star'>*</span> 이미지 첨부:</label>
			<div class='col-sm-4'>
			<input class='form-control' type='file' name='img' accept='.jpg, .png, .PNG, .JPG' required>
			</div>
			<div class='col-sm-2'>
			<button id='btn-del_${stepNumber}' type='button' class='btn btn-danger'>step 삭제</button>
			</div>
			</div>`

        $('#cookMethod').append(row);
        recipe.del();
        recipe.init();
    },

    del: function () {
        $('[id^="btn-del_"]').off().on('click', function () {

            $(this).closest('[id^="step_"]').remove();

            var steps = $('[id^="step_"]:last').index() + 1;
            for (var i = 0; i < steps; i++) {
                $('[id^="step_"]:eq(' + i + ') span:first').text('Step ' + (i + 1));
            }
            recipe.StepInit();
        });
    },


    cancle: function () {
        history.back();
    },

    StepInit: function () {
        var steps = $('[id^="step_"]:last').index() + 1;
        for (var i = 0; i < steps; i++) {
            $('[id^="step_"]:eq(' + i + ')').attr('id', 'step_' + (i + 1));
            $('[id^="btn_del_"]:eq(' + i + ')').attr('id', 'btn_del_' + (i + 1));
            $('[name=stepIndex]:eq(' + i + ')').val(i)
        }
    }
};
recipe.init();