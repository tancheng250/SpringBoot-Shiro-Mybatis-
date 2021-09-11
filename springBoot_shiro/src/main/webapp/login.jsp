<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isErrorPage="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>login</title>
</head>
<body>
<h1>登录页面</h1>
<form action="${pageContext.request.contextPath}/login" method="post" >
    账号：<input type="text" name="username">
    <br>
    密码：<input type="password" name="password">
    <input type="submit" value="login">
</form>
</body>
</html>