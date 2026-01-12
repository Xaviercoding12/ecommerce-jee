package com.example.ecommerce.ecommerceweb.servlet;

import com.example.ecommerce.ecommerceweb.entity.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Product> products = em
                .createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();

        request.setAttribute("products", products);
        request.getRequestDispatcher("/user/home.jsp").forward(request, response);
    }
}
