package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Hasmik on 28.06.2017.
 */
public class AddToFriendListServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserManager userManager = new UserManager();
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String friend_id = req.getParameter("id");
        userManager.addFriend(user.getId(),Long.parseLong(friend_id));
        resp.sendRedirect("/home");

    }
}
