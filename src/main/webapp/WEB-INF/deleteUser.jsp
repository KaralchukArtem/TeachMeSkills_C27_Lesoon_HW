<%--
  Created by IntelliJ IDEA.
  User: BlackOuT
  Date: 10.06.2024
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/delete" method="post">
  <label>Enter delete id: </label>
  <br>
  <input type="number" name="id"/>
  <br>
  <button type="submit">Show employee</button>
</form>
</body>
</html>
