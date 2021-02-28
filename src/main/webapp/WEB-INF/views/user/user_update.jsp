<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>레시피</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="_csrf_header" content="${_csrf.headerName}">
    <meta name="_csrf" content="${_csrf.token}">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/cropperjs/1.5.11/cropper.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jdenticon@3.1.0/dist/jdenticon.min.js" async
            integrity="sha384-VngWWnG9GS4jDgsGEUNaoRQtfBGiIKZTiXwm9KpgAeaRn6Y/1tAFiyXqSzqC8Ga/" crossorigin="anonymous">
    </script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/cropperjs/1.5.11/cropper.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cropper/1.0.1/jquery-cropper.js"></script>
    <style>
        .container {
            max-width: 100%;
        }
    </style>
    <script defer src="/js/user_update.js"></script>
</head>
<body>
<header>
    <%@include file="../common/nav.jsp" %>
    <input id="tab-info" type="hidden" value="<c:out value='${tabInfo}'/>">
</header>
<div class="container">
    <div class="row mt-5 justify-content-center">
        <div class="col-sm-12">
            <h1 class="text-center"><c:out value="${user.nickname}"/></h1>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-2">
            <ul class="nav nav-pills nav-stacked">
                <li id="profile-li" class="active"><a data-toggle="pill" href="#profile-tab">프로필 수정</a></li>
                <li id="password-li"><a data-toggle="pill" href="#password-tab">비밀번호 변경</a></li>
                <li id="account-li"><a data-toggle="pill" href="#account-tab">계정</a></li>
            </ul>
        </div>
        <div class="tab-content col-sm-8">
            <div id="profile-tab" class="tab-pane fade in active">
                <c:if test="${!empty message && tabInfo == 'profile'}">
                    <div class="alert alert-info" role="alert">
                        <span><c:out value="${message}"/></span>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>
                <div class="row mt-3">
                    <form class="col-sm-6 needs-validation" action="/user/update/profile" method="post" novalidate>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="form-group">
                            <label for="bio">한 줄 소개</label>
                            <input id="bio" type="text" name="bio" class="form-control"
                                   placeholder="간략한 소개를 부탁합니다." aria-describedby="bioHelp" required maxlength="30"
                                   value="<c:out value='${user.bio}'/>">
                            <small id="bioHelp" class="form-text text-muted">
                                길지 않게 35자 이내로 입력하세요.
                            </small>
                            <c:if test="${!empty errors['bio']}">
                                <small class="form-text text-danger">35자를 초과 하였습니다.</small>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label for="url">링크</label>
                            <input id="url" type="url" name="url" class="form-control"
                                   placeholder="http://recipe.com" aria-describedby="urlHelp" maxlength="50"
                                   value="<c:out value='${user.url}'/>">
                            <small id="urlHelp" class="form-text text-muted">
                                블로그, 유튜브 또는 포트폴리오나 좋아하는 웹 사이트 등 본인을 표현할 수 있는 링크를 추가하세요.
                            </small>
                            <c:if test="${!empty errors['url']}">
                                <small class="form-text text-danger">옳바른 URL이 아닙니다. 예시처럼 입력해 주세요.</small>
                            </c:if>
                        </div>

                        <div class="form-group">
                            <label for="company">직업</label>
                            <input id="company" type="text" name="occupation" class="form-control"
                                   placeholder="어떤 일을 하고 계신가요?" aria-describedby="occupationHelp" maxlength="30"
                                   value="<c:out value='${user.occupation}'/>">
                            <small id="occupationHelp" class="form-text text-muted">
                                개발자? 매니저? 취준생? 대표님?
                            </small>
                        </div>

                        <div class="form-group">
                            <label for="location">활동 지역</label>
                            <input id="location" type="text" name="location" class="form-control"
                                   placeholder="Redmond, WA, USA"
                                   aria-describedby="locationdHelp" maxlength="30"
                                   value="<c:out value='${user.location}'/>">
                            <small id="locationdHelp" class="form-text text-muted">
                                주요 활동(사는 곳이나 직장을 다니는 곳 또는 놀러 다니는 곳) 지역의 도시 이름을 알려주세요.
                            </small>
                        </div>

                        <div class="form-group">
                            <input id="profileImage" type="hidden" name="userImage" class="form-control"/>
                        </div>

                        <div class="form-group">
                            <button class="btn btn-primary btn-block" type="submit"
                                    aria-describedby="submitHelp">수정하기
                            </button>
                        </div>
                    </form>
                    <div class="col-sm-6">
                        <div class="panel panel-default text-center">
                            <div class="panel-heading">
                                프로필 이미지
                            </div>
                            <div id="current-profile-image">
                                <c:if test="${empty user.userImage}">
                                    <svg class="rounded" data-jdenticon-value="${user.nickname}" width="125"
                                         height="125"></svg>
                                </c:if>
                                <c:if test="${!empty user.userImage}">
                                    <img class="rounded" src="${user.userImage}"
                                         width="125" height="125" alt="${user.nickname}"/>
                                </c:if>
                            </div>
                            <div id="new-profile-image"></div>
                            <div class="panel-body">
                                <div class="custom-file">
                                    <label class="custom-file-label btn btn-info cropped-new-profile-imagebtn-block"
                                           for="profile-image-file" style="margin-bottom: 5px;">프로필 이미지 변경</label>
                                    <input type="file" class="custom-file-input" id="profile-image-file"
                                           style="display: none;">
                                </div>
                                <div id="new-profile-image-control">
                                    <button class="btn btn-primary btn-block" id="cut-button">자르기</button>
                                    <button class="btn btn-success btn-block" id="confirm-button">확인</button>
                                    <button class="btn btn-warning btn-block" id="reset-button">취소</button>
                                </div>
                                <div id="cropped-new-profile-image" class="mt-3"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="password-tab" class="tab-pane fade">
                <c:if test="${!empty message && tabInfo == 'password'}">
                    <div class="alert alert-info" role="alert">
                        <span><c:out value="${message}"/></span>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>
                <div class="row mt-3">
                    <form class="col-sm-9 needs-validation" action="/user/update/password" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="form-group">
                            <label for="newPassword">새 패스워드</label>
                            <input id="newPassword" type="password" name="newPassword" class="form-control"
                                   aria-describedby="passwordHelp" minlength="8" maxlength="50" required>
                            <small id="passwordHelp" class="form-text text-muted">
                                8자에서 50자 사이로 입력해주세요
                            </small>
                            <c:if test="${!empty errors['newPassword']}">
                                <small class="form-text text-danger"><c:out value="${errors['newPassword']}"/></small>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <label for="newPasswordConfirm">패스워드 확인</label>
                            <input id="newPasswordConfirm" type="password" name="newPasswordConfirm"
                                   class="form-control"
                                   aria-describedby="passwordConfirmHelp" minlength="8" maxlength="50" required>
                            <c:if test="${!empty errors['newPasswordConfirm']}">
                                <small class="form-text text-danger"><c:out
                                        value="${errors['newPasswordConfirm']}"/></small>
                            </c:if>
                        </div>
                        <div class="form-group">
                            <button class="btn btn-info btn-block" type="submit"
                                    aria-describedby="submitHelp">비밀번호 변경
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            <div id="account-tab" class="tab-pane fade">
                <h3>닉네임 변경</h3>
                <c:if test="${!empty message && tabInfo == 'account'}">
                    <div class="alert alert-info" role="alert">
                        <span><c:out value="${message}"/></span>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                </c:if>
                <p class="alert alert-warning py-5" role="alert">닉네임을 변경하면 프로필 페이지 링크도 바뀝니다.</p>
                <form class="col-12" action="/user/update/nickname" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="form-group">
                        <input aria-describedby="nicknameHelp" class="form-control" id="nickname" name="nickname"
                               required
                               maxlength="20"
                               minlength="3" type="text" value="<c:out value='${user.nickname}'/>">
                        <small class="form-text text-muted" id="nicknameHelp">
                            공백없이 문자와 숫자로만 3자 이상 20자 이내로 입력하세요. 가입후에 변경할 수 있습니다.<br/>
                        </small>
                        <c:if test="${errors['nickname'] ne null}">
                            <small class="form-text text-danger">Nickname
                                Error: <c:out value="${errors['nickname']}"/> </small>
                        </c:if>
                    </div>
                    <div class="form-group">
                        <button class="btn btn-info" type="submit"
                                aria-describedby="submitHelp">변경하기
                        </button>
                    </div>
                </form>
                <h3 style="color: darkred;">계정삭제</h3>
                <p class="alert alert-danger py-5" role="alert"> 이 계정은 삭제할 수 없습니다.</p>
                <form class="col-12" action="/user/delete" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="form-group">
                        <button class="btn btn-danger" type="submit" disabled>계정 삭제</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="../common/footer.html" %>
</body>
</html>