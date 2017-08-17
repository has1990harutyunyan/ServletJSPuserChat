package servlet;

import manager.MessageManager;
import model.Message;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hasmik on 02.07.2017.
 */
public class GetMessagesServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        MessageManager messageManager = new MessageManager();
        ArrayList<Message> allMessages = messageManager.getAllMessages(user.getId());
        if (allMessages != null) {
            req.setAttribute("allMessages", allMessages);
            req.getRequestDispatcher("/messages.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("/error.jsp");
        }

    }
}
