<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/2/20
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
${name}
<form action="/login" method="post">
    <input type="text" name="userName"><br>
    <input type="password" name="password"><br>
    <input type="submit" value="登陆">
</form>
</body>
</html>
