<%--
  Created by IntelliJ IDEA.
  User: 郑晓辉
  Date: 2017/3/13
  Time: 14:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆成功</title>
</head>
<body>
<h1 align="center">恭喜您已经成功登录！</h1>
<div align="center">
    欢迎你 ${user.userTel}
    <a href="/user/logout">退出</a>
</div>
</body>
</html>
