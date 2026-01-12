package com.example.ecommerce.ecommerceweb.servlet;

import com.example.ecommerce.ecommerceweb.dao.ProductDAO;
import com.example.ecommerce.ecommerceweb.entity.Product;
import com.example.ecommerce.ecommerceweb.model.Cart;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/user/add-to-cart")
public class AddToCartServlet extends HttpServlet {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long productId = Long.parseLong(request.getParameter("id"));

        ProductDAO productDAO = new ProductDAO(em);
        Product product = productDAO.findById(productId);

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
        }

        cart.addProduct(product);
        session.setAttribute("cart", cart);

        response.sendRedirect("cart.jsp");
    }
}
