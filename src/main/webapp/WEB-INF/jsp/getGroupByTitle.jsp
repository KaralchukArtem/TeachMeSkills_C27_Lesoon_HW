<%--
  Created by IntelliJ IDEA.
  User: BlackOuT
  Date: 12.08.2024
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/get" method="get">
    <label>Enter title group: </label>
    <br>
    <input type="text" name="title"/>
    <br>
    <button type="submit">Show group</button>
</form>
</body>
</html>
