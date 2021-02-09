<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>jsp test</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Springboot-jsp-mariadb(mybatis)</h1>
<button id="btn" class="btn btn-primary">recipes Api button</button>
<script>
    $("#btn").click(function() {
        $.ajax({
            url: "/recipes",
            type: "GET",                            // HTTP 요청 방식(GET, POST)
            success: function (response) {
                let recipes = response['recipes'];
                for(let i = 0; i<recipes.length; i++) {
                    console.log(recipes[i]);
                }
            }
        })
    })
</script>
</body>
</html>