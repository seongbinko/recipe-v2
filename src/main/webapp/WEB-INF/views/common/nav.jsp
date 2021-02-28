<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page import="com.seongbindb.recipe.dto.UserAccountDto" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../common/tag.jsp" %>
<%
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Object principal = auth.getPrincipal();
    String nickname = "";
    boolean emailVerified;
    //User user = null;
    
/*    if ( principal instanceof UserAccountDto ) System.out.println(" UserAccountDto 객체 맞음! ");
    else System.out.println(" UserAccountDto 객체아님 " + principal.getClass().toString());*/

    if ( principal != null && principal instanceof UserAccountDto) {
        //user = ((UserAccountDto)principal).getUser();
        nickname = ((UserAccountDto)principal).getUser().getNickname();
        emailVerified = (((UserAccountDto) principal).getUser().isEmailVerified());

        pageContext.setAttribute("nickname", nickname);
        pageContext.setAttribute("emailVerified", emailVerified);
        //pageContext.setAttribute("USER", user);
    }
//    else {
//        nickname = (String)principal ;
//        System.out.println("String 에서 가져온 닉네임 " + nickname);
//    }
%>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div>
            <ul class="nav navbar-nav">
                <li><a href="/recipes"><img src="/images/logo/logo.png" style="width: 10%;"/></a></li>
            </ul>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <sec:authorize access="isAnonymous()">
                <li><a href="/login"><i class="fa fa-sign-in" aria-hidden="true">로그인</i></a></li>
                <li><a href="/sign_up"><i class="fa fa-address-card" aria-hidden="true">회원가입</i></a></li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <input id="user_nickname" type="hidden" value="${nickname}"/>
                <li><a><span>${nickname}님 환영합니다.</span></a></li>
                <li><a href="/user/${nickname}"><i class="fa fa-user-circle-o" aria-hidden="true">프로필</i></a></li>
                <li>
                   <form id="logout-form" class="form-inline my-2 my-lg-0" action="/logout" method="post">
                       <input type="hidden" name="_csrf_header" value="${_csrf.headerName}">
                       <input type="hidden" name="_csrf" value="${_csrf.token}">
                   </form>
                   <a id="logout-btn"style="cursor: pointer"><i class="fa fa-sign-out" aria-hidden="true">로그아웃</i></a>
                </li>
                <script>
                    $("#logout-btn").click(function() {
                        $("#logout-form").submit();
                    })
                </script>
            </sec:authorize>
        </ul>
    </div>
</nav>
