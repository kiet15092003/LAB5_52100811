package servlet;

import dao.UserDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.User;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/register")
public class RegisterServlet extends HttpServlet {
    private UserDao userDAO;
    @Override
    public void init() throws ServletException {
        super.init();
        userDAO = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String confirm_password = req.getParameter("password-confirm");
        if (password.length()<6){
            req.setAttribute("falsePassword", "Password must have at least 6 characters!");
            req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
            return;
        }
        if(!confirm_password.equals(password)){
            req.setAttribute("falsePassword", "Your passwords are not the same");
            req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
            return;
        }
        User user = new User(name, password, email);
        userDAO.add(user);
        resp.sendRedirect("login");
    }
}
