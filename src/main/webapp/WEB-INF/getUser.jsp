<%--
  Created by IntelliJ IDEA.
  User: BlackOuT
  Date: 10.06.2024
  Time: 13:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/get" method="get">
    <label>Enter ID of employee to show it333: </label>
    <br>
    <input type="number" name="id"/>
    <br>
    <button type="submit">Show employee</button>
</form>
</body>
</html>
