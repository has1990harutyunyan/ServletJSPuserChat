package servlet;

import manager.UserManager;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hasmik on 28.06.2017.
 */
public class HomeServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        UserManager userManager = new UserManager();
        ArrayList<Long> list = userManager.getFriendId(user.getId());
        ArrayList<User> allFriends = new ArrayList<User>();
        for (Long aLong : list) {
            User friend = userManager.getUserById(aLong);
            allFriends.add(friend);
            req.setAttribute("allFriends", allFriends);

        }

        req.setAttribute("allUsers", userManager.getAllUsers(user.getId()));

        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
