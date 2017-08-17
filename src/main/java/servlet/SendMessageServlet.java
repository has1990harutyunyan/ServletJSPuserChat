package servlet;

import manager.MessageManager;
import model.Message;
import model.User;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class SendMessageServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String told = req.getParameter("told");
        String message = req.getParameter("message");
        String error = "";

        if (Validator.isEmpty(message)) {
            error += "Message is empty.";
        }


        if (error.equals("")) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            Message sendMessage = new Message();
            sendMessage.setFromId(user.getId());
            sendMessage.setToId(Long.parseLong(told));
            sendMessage.setMessage(message);

            MessageManager messageManager = new MessageManager();
            messageManager.sendMessage(sendMessage);
            resp.sendRedirect("/home");

        } else {
            resp.sendRedirect("/error.jsp");
        }

    }
}
