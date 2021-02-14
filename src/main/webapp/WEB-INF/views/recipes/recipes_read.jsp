<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Detail</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <style type="text/css"></style>
</head>
<body>
<header>
    <%@include file="../common/navheader.jsp" %>
</header>
<section>
    <div class="container" id="recipeNo" data-recipeno="${recipeInfo.recipe.recipeNo }">
        <div class="row">
            <div class="col-sm-12">
                <div class="">
                    <a href="${recipeInfo.recipeDetails[fn:length(recipeInfo.recipeDetails)-1].recipeDetailImg }"
                       target="_blank">
                        <img class="img-responsive"
                             src="${recipeInfo.recipeDetails[fn:length(recipeInfo.recipeDetails)-1].recipeDetailImg}"
                             style="height: 600px; width: 600px;"/>
                    </a>
                </div>
                <br>
                <div class="caption">
                    <h2>${recipeInfo.recipe.recipeName }</h2>
                    by<span> ${recipeInfo.recipe.nickName} </span>스크랩: <span id="scrap-amount"
                                                                             data-scrap-status="${recipeInfo.scrapStatus }">${fn:length(recipeInfo.recipeScraps) }</span>회
                    등록일: <span><fmt:formatDate value="${recipeInfo.recipe.recipeCreateDate}"
                                               pattern="yyyy-MM-dd HH:mm"/> </span>
                    수정일: <span><fmt:formatDate value="${recipeInfo.recipe.recipeModDate }"
                                               pattern="yyyy-MM-dd HH:mm"/> </span>
                    <div class="pull-right">
                        <c:choose>
                            <c:when test="${recipeInfo.role eq 'author'}">
                                <a class="btn btn-primary"
                                   href="/recipes/update/${recipeInfo.recipe.recipeNo }"><spring:message
                                        code="button.update"/></a>
                                <button id="delete-btn" class="btn btn-danger" value="${recipeInfo.recipe.recipeNo }">
                                    <spring:message code="button.delete"/></button>
                            </c:when>
                            <c:otherwise>
                                <button id="do-scrap" class="btn btn-default" type="button"
                                        value="${recipeInfo.recipe.recipeNo }">스크랩
                                </button>
                                <button id="cancel-scrap" class="btn btn-default" type="button"
                                        value="${recipeInfo.recipe.recipeNo }">스크랩 취소
                                </button>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-sm-12; form-horizontal">
                <div id="cookMethod">
                    <c:forEach var="recipeDetail" varStatus="vs" begin="0"
                               end="${fn:length(recipeInfo.recipeDetails)-2}" items="${recipeInfo.recipeDetails }">
                        <div id="step_1">
                            <div class="form-group">
                                <label class="control-label col-sm-2"><span>Step ${vs.index +1}</span></label>
                                <div class="col-sm-3" id="img_wrap">
                                    <a href="${recipeDetail.recipeDetailImg }" target="_blank">
                                        <img id="img" src="${recipeDetail.recipeDetailImg }" width="180px"
                                             height="180px" class=""/>
                                    </a>
                                </div>
                                <div class="form-group">
                                    <textarea rows="8" cols="70" style="resize: none;" placeholder="" name="content"
                                              disabled="disabled">${recipeDetail.content }</textarea>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                <hr>
                <div id="last">
                    <div class="form-group">
                        <label class="control-label col-sm-2">완성사진</label>
                        <div class="col-sm-10;">
                            <a href="${recipeInfo.recipeDetails[fn:length(recipeInfo.recipeDetails)-1].recipeDetailImg}"
                               target="_blank">
                                <img alt="" class="img-rounded"
                                     src="${recipeInfo.recipeDetails[fn:length(recipeInfo.recipeDetails)-1].recipeDetailImg}"
                                     style="width: 600px" height="600px"/>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="comment-box">
            <div id="comment-show"></div>
            <div id="comment-writer">
                <h3>${USER.nickname }
                    <button class="attach-comment">등록</button>
                </h3>
                <textarea rows="10" cols="124" placeholder="댓글을 남겨주세요" maxlength="200"></textarea>
            </div>
        </div>
    </div>
</section>
</body>
<script src="/js/recipe_read.js"></script>
</html>