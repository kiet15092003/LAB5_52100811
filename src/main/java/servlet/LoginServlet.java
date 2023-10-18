package servlet;
import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.User;
import java.io.IOException;

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        UserDao userDAO = new UserDao();
        User user = userDAO.getByUserName(username);
        if (user==null){
            resp.sendRedirect("register");
        }
        else if  (!user.getPassword().equals(password) || username.isEmpty() || password.isEmpty()){
            HttpSession session = req.getSession();
            session.setAttribute("flash_message", "Invalid username or password");
            req.getRequestDispatcher("jsp/login.jsp").forward(req, resp);
        }else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            session.setAttribute("username", username);
            String remember = req.getParameter("rememberPass");
            if (remember != null && remember.equals("remember")) {
                Cookie usernameCookie = new Cookie("username", username);
                usernameCookie.setMaxAge(30 * 24 * 60 * 60);
                resp.addCookie(usernameCookie);
                Cookie passwordCookie = new Cookie("password", password);
                passwordCookie.setMaxAge(30 * 24 * 60 * 60);
                resp.addCookie(passwordCookie);
            }
            resp.sendRedirect("product");
        }
    }
}
