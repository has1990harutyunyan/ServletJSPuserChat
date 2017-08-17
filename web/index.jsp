
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<% if (request.getAttribute("message") != null) {%>
<span> <%=request.getAttribute("message")%></span>
<%
    }
%>

<form action="/login" method="post">
    email: <br> <input type="text" name="email"> <br> <br>
    password: <br> <input type="password" name="password"> <br><br>

    <input type="submit" value="OK"> <br><br>
    <a href="register.jsp"> Register </a>


</form>


</body>
</html>
