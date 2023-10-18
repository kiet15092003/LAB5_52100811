package servlet;

import dao.ProductDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Product;

import java.io.IOException;
import java.util.List;

@WebServlet(value = "/product")
public class ProductServlet extends HttpServlet {
    private ProductDao productDao;

    public void init() {
        productDao = new ProductDao();
        List<Product> products = productDao.getAll();
        getServletContext().setAttribute("products", products);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        if (username!=null){
            req.setAttribute("username", username);
            List<Product> products = productDao.getAll();
            req.setAttribute("products", products);
            req.getRequestDispatcher("jsp/product.jsp").forward(req, resp);
        }
        resp.sendRedirect("login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String priceStr = req.getParameter("price");
        String idStr = req.getParameter("id");
        if (idStr==null){
            if (name.isEmpty()) {
                req.setAttribute("errorMessage", "Vui lòng nhập tên sản phẩm");
                req.getRequestDispatcher("jsp/product.jsp").forward(req, resp);
            } else if (!name.isEmpty() && priceStr.isEmpty()) {
                req.setAttribute("errorMessage", "Vui lòng nhập đầy đủ thông tin");
                req.getRequestDispatcher("jsp/product.jsp").forward(req, resp);
            } else{
                int price = Integer.parseInt(priceStr);
                Product product = new Product(name, price);
                productDao.add(product);
                //resp.sendRedirect("product");
                List<Product> products = productDao.getAll();
                req.setAttribute("products", products);
                req.getRequestDispatcher("jsp/product.jsp").forward(req, resp);
            }
        } else{
            int idPro = Integer.parseInt(idStr);
            productDao.removeById(idPro);
            resp.sendRedirect("product");
        }
    }
}
