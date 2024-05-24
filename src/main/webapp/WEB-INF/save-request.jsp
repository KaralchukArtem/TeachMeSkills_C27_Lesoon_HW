<html>
<head>
    <title>Title</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <label>Name:
            <input type="text" name="name"><br />
        </label>
        <label>Password:
            <input type="password" name="pass"><br />
        </label>
        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
