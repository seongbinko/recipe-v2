<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Main</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="page-header">
        <h1>Home page</h1>
    </div>
    <div class="row">
        <div class="col-sm-12">
            <table class="table">
                <thead>
                <tr>
                    <th>번호</th>
                    <th>아이디</th>
                    <th>패스워드</th>
                    <th>닉네임</th>
                    <th>생성일</th>
                    <th>전화번호</th>
                    <th>권한</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>${user.no }</td>
                    <td>${user.id }</td>
                    <td>${user.password }</td>
                    <td>${user.nickname }</td>
                    <td>${user.createDate }</td>
                    <td>${user.phoneNo }</td>
                    <td>${user.role }</td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>
</html>