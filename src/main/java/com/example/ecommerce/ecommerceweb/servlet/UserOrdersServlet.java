package com.example.ecommerce.ecommerceweb.servlet.user;

import com.example.ecommerce.ecommerceweb.entity.Order;
import com.example.ecommerce.ecommerceweb.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/orders")
public class UserOrdersServlet extends HttpServlet {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        List<Order> orders = em.createQuery(
                        "SELECT o FROM Order o WHERE o.user = :user ORDER BY o.orderDate DESC",
                        Order.class
                )
                .setParameter("user", user)
                .getResultList();

        request.setAttribute("orders", orders);

        try {
            request.getRequestDispatcher("/user/orders.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
