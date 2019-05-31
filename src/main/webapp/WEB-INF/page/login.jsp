<%--
  Created by IntelliJ IDEA.
  User: LL
  Date: 2019-05-31
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>登录</title>
    <script src="${pageContext.request.contextPath}/static/js/login.js" type="text/javascript"></script>
    <script src="${pageContext.request.contextPath}/static/js/jquery-2.1.1.min.js" type="text/javascript"></script>
</head>
<body>
    用户名:<input type="text" id="username" />
    密码:<input type="password" id="password" />
    <input type="button" value="登录" onclick="login();" />
</body>
</html>
