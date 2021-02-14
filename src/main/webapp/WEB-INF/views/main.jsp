<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Main</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
    <%@include file="common/navheader.jsp" %>
</header>
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
            <div class="col-sm-4">
                <a class="btn btn-primary pull-right" href="/recipes/update">레시피 등록</a>
                <input type="hidden" id="pageNo" value="">
            </div>
        </div>
        <br/>
        <div class="row">
            <nav class="navbar navbar-default">
                <div class="container">
                    <ul id="category-box" class="nav nav-pills sb">
                        <li class="active"><a>ALL</a></li>
                        <li value="10000"><a>한식</a></li>
                        <li value="10001"><a>중식</a></li>
                        <li value="10002"><a>일식</a></li>
                        <li value="10003"><a>양식</a></li>
                        <li value="10004"><a>기타</a></li>
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
</body>
</html>