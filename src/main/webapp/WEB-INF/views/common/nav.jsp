<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../common/tag.jsp" %>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div>
            <ul class="nav navbar-nav">
                <li><a href="/recipes"><img src="/images/logo/logo.png" style="width: 10%;"/></a></li>
            </ul>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <c:if test="${empty USER}">
                <li><a href="/login"><span class="glyphicon glyphicon-log-in"></span> 로그인</a></li>
                <li><a href="/sign-up"><span class="glyphicon glyphicon-user"></span> 회원가입</a></li>
            </c:if>
            <c:if test="${not empty USER}">
                <li style="margin-top: 15px;" id="nickname" data-user-nickname="${USER.nickname }">
                    환영합니다. ${USER.nickname}님
                </li>
                <li><a href="/logout"><span class="glyphicon glyphicon-log-out">로그아웃</span></a></li>
            </c:if>
        </ul>
    </div>
</nav>
