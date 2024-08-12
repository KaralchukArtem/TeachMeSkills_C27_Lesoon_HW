<%--
  Created by IntelliJ IDEA.
  User: BlackOuT
  Date: 12.08.2024
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="getGroupByTitle.jsp" />
<jsp:include page="getAllStudentsDesc.jsp"/>
<form action="${pageContext.request.contextPath}/top" method="get">
    <button type="submit">Show top students </button>
</form>
<form action="${pageContext.request.contextPath}/avg" method="get">
    <button type="submit">Show avg students </button>
</form>
</body>
</html>
