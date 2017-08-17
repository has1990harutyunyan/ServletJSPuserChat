package servlet;


import manager.UserManager;
import model.User;
import util.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Hasmik on 28.06.2017.
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        String message = "";
        if (Validator.isEmpty(email)) {
            message += "Email is empty.";
        }
        if (Validator.isEmpty(password)) {
            message += "Password is empty.";
        }
        UserManager userManager = new UserManager();
        if (message.equals("")) {
            User user = userManager.getUserByEmailAndPassword(email, password);
            if (user != null) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);

                session.setAttribute("welcomeMessage", "welcome");
                req.getRequestDispatcher("/home").forward(req, resp);

            } else {
                req.setAttribute("message", "Invalid username or password.");
                req.getRequestDispatcher("index.jsp").forward(req, resp);

            }


        } else {
            req.setAttribute("message", message);
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }
    }
}
