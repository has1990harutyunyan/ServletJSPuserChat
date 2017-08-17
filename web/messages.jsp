<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Message" %><%--
  Created by IntelliJ IDEA.
  User: Hasmik
  Date: 02.07.2017
  Time: 1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Messages</title>
</head>
<body>
<%User user = (User) request.getSession().getAttribute("user");%>

<% if (session.getAttribute("welcomeMessage") != null) { %>
<%= user.getName() + ", " + session.getAttribute("welcomeMessage") + " !!!"%>
<%}%>
<br> <br>

<h3> Message list: </h3>
<table border="1">
    <tr>
        <th> From</th>
        <th> Message</th>
        <th> Date</th>
    </tr>
    <%
        ArrayList<Message> allMessages = (ArrayList<Message>) request.getAttribute("allMessages");
        for (Message message : allMessages) {
    %>

    <tr>
        <td><%=message.getFromUser().getName()%> <%=message.getFromUser().getSurname()%>
        </td>
        <td><%=message.getMessage()%>
        </td>
        <td><%=message.getTimestamp()%>
        </td>
    </tr>
    <%
        }
    %>

</table>

<h3><a href="/home"> back </a></h3>
<h3><a href="/logout"> log out </a></h3>
</body>
</html>
