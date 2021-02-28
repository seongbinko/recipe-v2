<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
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
    <style type="text/css">
        .sb > li.active > a, .sb > li.active > a:focus, .sb > li.active > a:hover {
            background-color: #333;
        }

        .sb > li > a {
            color: black;
        }
    </style>
    <script defer src="/js/main.js"></script>
</head>
<body>
<header>
    <%@include file="common/nav.jsp" %>
</header>
<c:if test="${nickname ne null && !emailVerified}">
    <p class="alert alert-warning py-5 text-center" role="alert"> 레시피 가입을 완료하려면 <a href="/check_email" class="alert-link">계정 인증 이메일을 확인</a>하세요.
        가입 인증을 하지 않으면 서비스 이용이 제한적입니다.
    </p>
</c:if>
<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-4"></div>
            <div class="col-sm-4">
                <div class="input-group">
                    <input id="search-input" type="text" class="form-control" placeholder="레시피 제목을 검색해주세요"
                           name="keyword"/>
                    <div class="input-group-btn">
                        <button id="search-icon" class="btn btn-default">
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </div>
                </div>
            </div>
            <c:if test="${nickname ne null && emailVerified}">
            <div class="col-sm-4">
                <a class="btn btn-primary pull-right" href="/recipes/update">레시피 등록</a>
                <input type="hidden" id="pageNo" value="">
            </div>
            </c:if>
        </div>
        <br/>
        <div class="row">
            <nav class="navbar navbar-default">
                <div class="container">
                    <ul id="category-box" class="nav nav-pills sb">
                        <li class="active"><a>ALL</a></li>
                        <li value="1"><a>한식</a></li>
                        <li value="2"><a>중식</a></li>
                        <li value="3"><a>일식</a></li>
                        <li value="4"><a>양식</a></li>
                        <li value="5"><a>기타</a></li>
                    </ul>
                </div>
            </nav>
        </div>
        <div class="row">
            <div class="pull-right">
                <div class="form-group">
                    <select class="form-control ghorder" id="selectBox">
                        <option value="date" selected>최신순</option>
                        <option value="scrap"> 스크랩순</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="row" id="show-recipes"></div>
        <div class="w3-center">
            <div id="page-box" class="w3-bar w3-border w3-round"></div>
        </div>
    </div>
</section>
<%@include file="common/footer.html" %>
</body>
</html>