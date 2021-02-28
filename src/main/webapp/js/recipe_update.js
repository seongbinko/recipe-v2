
$(document).ready(function () {
    // 주소를 따온다.
    var pathname = $(location).attr('pathname')
    //recipeNo를 추출한다.
    var recipeNo = pathname.split('/').pop();


    $("#recipe-registerform").attr('action', '/api/recipes/' + recipeNo);

    $.ajax({
        type: "GET",
        url: "/api/recipes/" + recipeNo,
        dataType: "json",
        error: function () {
            alert("알수없는 에러가 발생하였습니다");
        },
        success: function (result) {

            var recipe = result.recipe;
            var recipeCategory = result.recipeCategory;
            $("#recipeNo").val(recipe.recipeNo);
            $("#title").val(recipe.recipeName);
            $("#selectBox").val(recipeCategory.recipeCategoryName);

            var length = result.recipeDetails.length; // 3

            var recipeDetails = result.recipeDetails
            $("#pre_img_1").attr('src', recipeDetails[0].recipeDetailImg);
            $("#textarea_1").val(recipeDetails[0].content);
            $("#recipeDetailNo-first").val(recipeDetails[0].recipeDetailNo);
            $("#pre_img_last").attr('src', recipeDetails[length - 1].recipeDetailImg);
            $("#recipeDetailNo-last").val(recipeDetails[length - 1].recipeDetailNo);

            if (2 < length) {
                for (var i = 1; i < length - 1; i++) {
                    var stepNumber = $('[id^="step_"]:last').index() + 2;
                    var index = $('[id^="step_"]:last').index() + 1;

                    var row = ` 
					<div id='step_${stepNumber}' class='preview-img'>
					<div class='form-group'>
					<label class='control-label col-sm-2'><span>Step ${stepNumber}</span></label>
					<input type='hidden' name='stepIndex' value='${index}'>
					<input type='hidden' name='recipeDetailNo' value='${recipeDetails[i].recipeDetailNo}'/></label>
					<div class='col-sm-3' id='img_wrap'>
					<img name='pre_img_${stepNumber}'alt='cooking' src='${recipeDetails[i].recipeDetailImg}' width='180px' height='180px'></div>
					<div class='form-group'>
					<textarea rows='8' cols='70' style='resize: none;' placeholder='요리방법을 작성해주세요' name='content' required>${recipeDetails[i].content}</textarea>
					</div>
					</div>
					<div class='form-group'>
					<label class='control-label col-sm-2'><span id='star'>*</span> 이미지 첨부:</label>
					<div class='col-sm-4'>
					<label class='btn btn-default btn-file'>파일변경<input class='form-control' type='file' accept='.jpg, .png, .PNG, .JPG' name='img' style='display: none'>
					</div>
					<div class='col-sm-2'>
					<button id='btn-del_${stepNumber}' type='button' class='btn btn-danger'>step 삭제</button>
					</div>
					</div>`

                    $('#cookMethod').append(row);

                    $('[id^="btn-del_"]').off().on('click', function () {

                        $(this).closest('[id^="step_"]').remove();

                        var steps = $('[id^="step_"]:last').index() + 1;
                        for (var i = 0; i < steps; i++) {
                            $('[id^="step_"]:eq(' + i + ') span:first').text('Step ' + (i + 1));
                        }
                    });
                }
                $(":input[name='img']").off().on('change', function (e) {
                    if ($(this).val() != "") {
                        var ext = $(this).val().split('.').pop().toLowerCase();
                        if ($.inArray(ext, ['png', 'jpg', 'jpeg']) == -1) {
                            alert("png,jpg 파일만 업로드 해주세요");
                            $(this).val("");
                            return;
                        }

                        var fileSize = this.files[0].size;
                        var maxSize = 5 * 1024 * 1024;
                        if (fileSize > maxSize) {
                            alert("파일용량이 5MB를 초과하였습니다");
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

                        var reader = new FileReader();
                        reader.onload = function (e) {
                            _this.attr("src", e.target.result);
                        }
                        reader.readAsDataURL(f);
                    });
                });
            }
        }
    })
})
