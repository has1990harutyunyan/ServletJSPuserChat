package servlet;
import manager.UserManager;
import model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;


public class RetrieveProfilePicServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User user = (User) req.getSession().getAttribute("user");
        UserManager userManager = new UserManager();
        String profilePic = userManager.getProfilePic(user.getId());
        if (profilePic != null) {
            String filePath = "E:\\userChat\\" + profilePic;


            resp.setContentType("image/jpg");

            File imageFile = new File(filePath);
            resp.setContentLength((int) imageFile.length());
            FileInputStream in = new FileInputStream(imageFile);
            OutputStream out = resp.getOutputStream();
            byte[] buf = new byte[5000];
            int length;
            while ((length = in.read(buf)) >= 0) {
                out.write(buf, 0, length);
            }

            in.close();
            out.close();

            req.getRequestDispatcher("/home").forward(req, resp);
        } else {
            resp.setContentType("image/jpg");
            File customImage = new File("E:\\userChat\\icon.jpg");
            resp.setContentLength((int) customImage.length());
            FileInputStream in = new FileInputStream(customImage);
            OutputStream out = resp.getOutputStream();
            byte[] buf = new byte[5000];
            int length;
            while ((length = in.read(buf)) >= 0) {
                out.write(buf, 0, length);
            }
            in.close();
            out.close();
            req.getRequestDispatcher("/home").forward(req, resp);
        }

    }

}