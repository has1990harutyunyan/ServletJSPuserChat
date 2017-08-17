<%@ page import="model.User" %>
<%@ page import="java.util.ArrayList" %>
<% User user = (User) session.getAttribute("user");%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
</head>
<body>

<img src="/profilePic" style="width: 400px"> <br> <br>

<% if (session.getAttribute("welcomeMessage") != null) { %>
<%= user.getName() + ", " + session.getAttribute("welcomeMessage") + " !!!"%>
<%}%>
<br> <br>

<a href="/messages">view all messages</a>
<br> <br>

<h3>Friend list: </h3>
<br>
<div style="border: solid; width: 400px; height: 200px">
    <%if (request.getAttribute("allFriends") != null) {%>
    <%ArrayList<User> friendList = (ArrayList<User>) request.getAttribute("allFriends");%>

    <table>
        <%for (User allFriend : friendList) {%>
        <tr>
            <td width="70px"><%=allFriend.getName()%>
            </td>
            <td width="110px"><%=allFriend.getSurname()%>
            </td>
            <td width="110px">
                <a href="sendMessage.jsp?told=<%=allFriend.getId()%>&name=<%=allFriend.getName()%>&surname=<%=allFriend.getSurname()%>">
                    send message</a></td>
            <td><a href="/deleteFriend?id=<%=allFriend.getId()%>"> delete </a></td>

        </tr>
        <%
            }
        %>
    </table>

    <%
        }
    %>
</div>
<h3>All users: </h3>
<div style="border: solid; width: 400px; height: 200px">
    <table>
        <tr>
            <th width="80px" align="left"> name</th>
            <th width="150px" align="left"> surname</th>
            <th width="50" align="left"> age</th>
            <th> Add friend</th>
        </tr>

        <% if (request.getAttribute("allUsers") != null) {%>
        <% ArrayList<User> userList = (ArrayList<User>) request.getAttribute("allUsers");%>


        <% for (User allUser : userList) {%>
        <tr>
            <td><%=allUser.getName()%>
            </td>

            <td><%=allUser.getSurname()%>
            </td>

            <td><%=allUser.getAge()%>
            </td>

            <td><a href="/addFriend?id=<%=allUser.getId()%>"> Add </a>

            </td>
        </tr>

        <%
            }
        %>
    </table>
    <%
        }
    %>

</div>
<h3><a href="/logout"> log out </a></h3>
</body>
</html>
