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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jdenticon@3.1.0/dist/jdenticon.min.js" async
            integrity="sha384-VngWWnG9GS4jDgsGEUNaoRQtfBGiIKZTiXwm9KpgAeaRn6Y/1tAFiyXqSzqC8Ga/" crossorigin="anonymous">
    </script>
    <style>
        .container {
            max-width: 100%;
        }
    </style>
</head>
<body>
<header>
    <%@include file="../common/nav.jsp" %>
</header>
<div class="container">
    <div class="row mt-5 justify-content-center">
        <div class="col-sm-1"></div>
        <div class="col-sm-2">
        <c:if test="${empty user.userImage}">
        <svg data-jdenticon-value="${user.nickname}" width="125" height="125" class="img-fluid float-left rounded img-thumbnail"></svg>
        </c:if>
        <c:if test="${!empty user.userImage}">
            <img class="img-fluid float-left rounded img-thumbnail" src="${user.userImage}" width="125" height="125">
        </c:if>
        </div>
        <div class="col-sm-8">
            <h1 class="display-4"><c:out value="${user.nickname}"/></h1>
            <c:if test="${!empty user.bio}">
                <c:out value="${user.bio}"/>
            </c:if>
            <c:if test="${empty user.bio}"> <!-- isOwner == true -->
                <p class="lead">한 줄 소개를 추가해 주세요</p>
            </c:if>
        </div>
        <div class="col-sm-1"></div>
    </div>
    <div class="row mt-3 justify-content-center">
        <div class="col-sm-1"></div>
        <div class="col-sm-2">
            <ul class="nav nav-pills nav-stacked">
                <li class="active"><a data-toggle="pill" href="#home">소개</a></li>
                <li><a data-toggle="pill" href="#menu1">게시글</a></li>
                <li><a data-toggle="pill" href="#menu2">스크랩</a></li>
            </ul>
        </div>
        <div class="tab-content col-sm-8">
            <div id="home" class="tab-pane fade in active">
                <p>
                    <span style="font-size: 20px;">
                        <i class="fa fa-envelope-o"></i>
                    </span>
                    <span class="col-9"><c:out value="${user.email}" /></span>
                </p>
                <p>
                    <span style="font-size: 20px;">
                        <i class="fa fa-link col-1"></i>
                    </span>
                    <span class="col-9"><c:out value="${user.url eq null || user.url eq '' ? '-' : user.url}" /></span>
                </p>
                <p>
                    <span style="font-size: 20px;">
                        <i class="fa fa-briefcase col-1"></i>
                    </span>
                    <span class="col-9"><c:out value="${user.occupation eq null || user.occupation eq '' ? '-' : user.occupation }" /></span>
                </p>
                <p>
                    <span style="font-size: 20px;">
                        <i class="fa fa-location-arrow col-1"></i>
                    </span>
                    <span class="col-9"><c:out value="${user.location eq null || user.location eq '' ? '-' : user.location }" /></span>
                </p>
                <c:if test="${isOwner || user.emailVerified}">
                <p>
                    <span style="font-size: 20px;">
                        <i class="fa fa-calendar-o col-1"></i>
                    </span>
                    <c:choose>
                        <c:when test="${isOwner && !user.emailVerified}">
                            <span class="col">
                                <a href="/check_email">가입을 완료하려면 이메일을 확인하세요.</a>
                            </span>
                        </c:when>
                        <c:otherwise>
                            <span class="col-9">가입일 <javatime:format value="${user.createDate}" pattern="yyyy-MM-dd"/></span>
                        </c:otherwise>
                    </c:choose>
                </p>
                </c:if>

                <c:if test="${isOwner}">
                    <div>
                        <a class="btn btn-info" href="/user/update">프로필 수정</a>
                    </div>
                </c:if>
            </div>
            <div id="menu1" class="tab-pane fade">
                <h3>게시글</h3>
                <p>서비스 준비중입니다.</p>
            </div>
            <div id="menu2" class="tab-pane fade">
                <h3>스크랩</h3>
                <p>서비스 준비중입니다.</p>
            </div>
        </div>
        <div class="col-sm-1"></div>
    </div>
</div>
<%@include file="../common/footer.html" %>
</body>
</html>