package servlet;

import manager.UserManager;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(50 * 1024);
            // Location to save data that is larger than maxMemSize.
//            factory.setRepository(new File("c:\\temp"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // maximum file size to be uploaded.
            upload.setSizeMax(9900 * 1024);

            try {
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(req);
                // Process the uploaded file items
                Iterator i = fileItems.iterator();
                User user = new User();
                UserManager userManager = new UserManager();
                while (i.hasNext()) {
                    FileItem fi = (FileItem) i.next();
                    if (!fi.isFormField()) {
                        String fileName = fi.getName();
                        File file = new File("E:\\userChat\\" +
                                "file_" + System.currentTimeMillis() + fileName);
                        fi.write(file);
                        user.setProfilePic(file.getName());
                    } else if (fi.getFieldName().equals("name")) {
                        user.setName(fi.getString());
                    } else if (fi.getFieldName().equals("surname")) {
                        user.setSurname(fi.getString());
                    } else if (fi.getFieldName().equals("age")) {
                        user.setAge(Integer.parseInt(fi.getString()));
                    } else if (fi.getFieldName().equals("email")) {
                        if (userManager.isEmailExist(fi.getString()))
                            user.setEmail(fi.getString());
                        else {
                            req.setAttribute("emailRepeat", "User with such email already exists." );
                            req.getRequestDispatcher("/register.jsp").forward(req,resp);
                        }
                    } else if (fi.getFieldName().equals("password")) {
                        user.setPassword(fi.getString());
                    }
                }
                userManager.addUser(user);
                HttpSession session = req.getSession();
                session.setAttribute("message", "you have registered successfully");
                resp.sendRedirect("/index.jsp");
            } catch (Exception e) {
                e.printStackTrace();
                resp.sendRedirect("/error.jsp");
            }


        }
    }
}