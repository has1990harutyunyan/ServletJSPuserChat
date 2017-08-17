<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<%if (request.getAttribute("emailRepeat") != null) {%>
<h3> <%=request.getAttribute("emailRepeat")%></h3>
<%}%>
<form action="/register" method="post" enctype="multipart/form-data">
    name: <br> <input type="text" name="name"> <br> <br>
    surname: <br> <input type="text" name="surname"> <br> <br>
    age:<br> <input type="number" name="age"> <br> <br>
    email: <br> <input type="text" name="email"> <br> <br>
    password: <br> <input type="text" name="password"> <br> <br>
    upload a file: <br> <input type="file" name="profilePic">

    <input type="submit" value="OK">
</form>


</body>
</html>
