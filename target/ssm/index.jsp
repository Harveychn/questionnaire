<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ include file="/WEB-INF/view/common/tagPage.jsp" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>首页</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
</head>
<body>
<h1 align="center">登录界面</h1>
<%--<div><label>${user.userTel}&nbsp;${user.password}</label></div>--%>
<div align="center">
    <form action="/user/login" method="post">
        <label>用户名：<input type="text" name="userTel" value="${user.userTel}" placeholder="input username"/></label>
        <label>密码：<input type="password" name="password" value="${user.password}" placeholder="input password"/></label><br>
        <input type="submit" value="登录"/><br>
        <label style="color: red; font-size: small;">${error}</label>
    </form>
</div>

</body>
</html>
