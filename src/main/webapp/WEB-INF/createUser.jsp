<%--
  Created by IntelliJ IDEA.
  User: BlackOuT
  Date: 10.06.2024
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/create" method="post">
    <label>Create employee: </label>
    <br>
    <label>ID</label>
    <input type="number" name="id"/>
    <br>
    <label>Name</label>
    <input type="text" name="name"/>
    <br>
    <label>surname</label>
    <input type="text" name="surname"/>
    <br>
    <label>age</label>
    <input type="number" name="age"/>
    <br>
    <label>passport_number</label>
    <input type="text" name="passport_number"/>
    <br>
    <button type="submit">Show employee</button>
</form>
</body>
</html>
