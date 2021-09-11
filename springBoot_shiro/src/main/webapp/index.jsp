<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page pageEncoding="UTF-8" isErrorPage="false" contentType="text/html; UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>index</title>
</head>
<body>
<h1>首页</h1>
<a href="${pageContext.request.contextPath}/logout">退出登录</a>
<ul>
<%--   有任意一个确实--%>
    <shiro:hasAnyRoles name="user,admin">
        <li>
            <a href="">用户管理</a>
            <ul>
<%--                角色操作--%>
                <shiro:hasPermission name="user:select">
                    <li>select·</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:update">
                   <li>update·</li>
                </shiro:hasPermission>
                <shiro:hasPermission name="user:insert">
                    <li>insert·</li>
                </shiro:hasPermission>
               <shiro:hasPermission name="user:delete">
                   <li>delete·</li>
               </shiro:hasPermission>
            </ul>
        </li>
    </shiro:hasAnyRoles>
<%--    包含admin--%>
    <shiro:hasRole name="admin">
        <li><a href="">仓库管理</a></li>
        <li><a href="">订单管理</a></li>
        <li><a href="">迷信管理</a></li>
    </shiro:hasRole>
    <li><a href="">奥数管理</a></li>
</ul>

<%--<ul>--%>
<%--        <li>--%>
<%--            <a href="">用户管理</a>--%>
<%--                <ul>--%>
<%--                    <li>select·</li>--%>
<%--                    <li>update·</li>--%>
<%--                    <li>insert·</li>--%>
<%--                    <li>delete·</li>--%>
<%--                </ul>--%>
<%--        </li>--%>
<%--        <li><a href="">仓库管理</a></li>--%>
<%--        <li><a href="">订单管理</a></li>--%>
<%--        <li><a href="">迷信管理</a></li>--%>
<%--        <li><a href="">奥数管理</a></li>--%>
<%--</ul>--%>
</body>
</html>