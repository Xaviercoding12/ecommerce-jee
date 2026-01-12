package com.example.ecommerce.ecommerceweb.servlet.admin;

import com.example.ecommerce.ecommerceweb.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/orders")
public class AdminOrdersServlet extends HttpServlet {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        List<Order> orders = em.createQuery(
                "SELECT o FROM Order o ORDER BY o.orderDate DESC",
                Order.class
        ).getResultList();

        request.setAttribute("orders", orders);

        try {
            request.getRequestDispatcher("/admin/orders.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
