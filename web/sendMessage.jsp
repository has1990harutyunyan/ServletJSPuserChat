<%@ page import="model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send message</title>
</head>
<body>
<%User user = (User) request.getSession().getAttribute("user");%>

<% if (session.getAttribute("welcomeMessage") != null) { %>
<%= user.getName() + ", " + session.getAttribute("welcomeMessage") + " !!!"%>
<%}%>
<h3> Send message: </h3>
<div>
    <form action="/sendMessage" method="post">
        <input type="hidden" name="told" value="<%=request.getParameter("told")%>">
        to: <span><%=request.getParameter("name")%> <span><%=request.getParameter("surname")%> </span> <br> <br>
   <h3> message </h3>
    <textarea name="message"> </textarea> <br> <br>
      <input type="submit" value="send">
    </form>


</div>


</body>
</html>
