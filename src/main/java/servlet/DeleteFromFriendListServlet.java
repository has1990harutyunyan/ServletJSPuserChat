package servlet;

import manager.UserManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Hasmik on 28.06.2017.
 */
public class DeleteFromFriendListServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        UserManager userManager = new UserManager();
        userManager.deleteFromFriendList(Long.parseLong(id));
        resp.sendRedirect("/home");

    }
}
