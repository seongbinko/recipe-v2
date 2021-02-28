<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>레시피</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jdenticon@3.1.0/dist/jdenticon.min.js" async
            integrity="sha384-VngWWnG9GS4jDgsGEUNaoRQtfBGiIKZTiXwm9KpgAeaRn6Y/1tAFiyXqSzqC8Ga/" crossorigin="anonymous">
    </script>
    <style type="text/css">
        #requiredNotice {
            margin-top: 25px;
        }

        .star {
            color: red;
        }
    </style>
    <script defer src="/js/recipe_save.js"></script>
</head>
<body>
<header>
    <%@ include file="../common/nav.jsp" %>
</header>
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-2">
                <h2 id="section-title">레시피 등록</h2>
            </div>
            <div class="col-sm-8">
                <p id="requiredNotice"><span class="star">*</span> 필수입력 사항 | 이미지는 jpg,png 형식만 가능하며 용량은 5mb이하로 제한됩니다.</p>
            </div>
        </div>
        <form id="recipe-registerform" class="form-horizontal well" method="post" enctype="multipart/form-data"
              action="/api/recipes" onsubmit="return checkSave()">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label class="control-label col-sm-2"><span class="star">*</span> 레시피 제목:</label>
                <div class="col-sm-10">
                    <input id="recipeNo" type="hidden" name="recipeNo"/>
                    <input type="text" class="form-control" id="title" placeholder="레시피 제목을 입력하세요" name="recipeName"
                           required minlength="5" maxlength="30"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2"><span class="star">*</span> 카테고리:</label>
                <div class="col-sm-2">
                    <select id="selectBox" class="form-control" name="categoryName" required>
                        <option disabled selected>목록</option>
                        <option value="한식">한식</option>
                        <option value="중식">중식</option>
                        <option value="일식">일식</option>
                        <option value="양식">양식</option>
                        <option value="기타">기타</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2"> 요리방법:</label>
                <div class="col-sm-3">
                    <button id="btn-add" type="button" class="btn btn-default">Step 추가</button>
                </div>
            </div>
            <hr>
            <div id="cookMethod">
                <div id="step_1" class="preview-img">
                    <div class="form-group">
                        <label class="control-label col-sm-2"><span>Step 1</span></label>
                        <input id="recipeDetailNo-first" type="hidden" name="recipeDetailNo" value="">
                        <input type="hidden" name="stepIndex" value="0">
                        <div class="col-sm-3" id="img_wrap">
                            <img id="pre_img_1" name="pre_img_1" src="/images/logo/biglogo.png" width="180px;">
                        </div>
                        <div class="form-group">
                            <textarea id="textarea_1" rows="8" cols="70" style="resize: none;"
                                      placeholder="요리방법을 작성해주세요" name="content" required></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-sm-2"><span class="star">*</span> 이미지 첨부:</label>
                        <div class="col-sm-4">
                            <input id="file_1" class="form-control" type="file" accept=".jpg, .png, .PNG, .JPG"
                                   name="img" max="" required/>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <div id="last" class="preview-img">
                <div class="form-group">
                    <label class="control-label col-sm-2">완성사진</label>
                    <input id="recipeDetailNo-last" type="hidden" name="recipeDetailNo" value="">
                    <input type="hidden" name="stepIndex">
                    <input type="hidden" name="content">
                    <div class="col-sm-offset-2 col-sm-6">
                        <img id="pre_img_last" alt="" name="pre_img_last" src="/images/logo/biglogo.png"
                             width="400px;" height="400px;">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-offset-2 col-sm-2"><span class="star">*</span> 이미지 첨부:</label>
                    <div class="col-sm-5">
                        <input class="form-control" type="file" name="img" accept=".jpg, .png, .PNG, .JPG" required/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-4">
                    <button id="btn-update" type="submit" class="btn btn-primary btn-lg" value="">등록</button>
                    <button id="btn-cancle" type="button" class="btn btn-default btn-lg" value="">취소</button>
                </div>
            </div>
        </form>
    </div>
</section>
</body>

</html>