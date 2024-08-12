<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
<h2>Students in Group</h2>
<div>
  <c:forEach items="${group}" var="entry">
    Key = ${entry.key},
    <c:forEach items="${entry.value}" var="student">
      <p>Name : ${student.name}</p>
      <p>Age : ${student.age}</p>
      <p>Group : ${student.grooupModel.title}</p>
      <p>Rating : ${student.recordBookModel.rating}</p>
      <br>
    </c:forEach>
  </c:forEach>
</div>
</body>
</html>
