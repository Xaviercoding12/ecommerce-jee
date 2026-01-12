package com.example.ecommerce.ecommerceweb.servlet.admin;

import com.example.ecommerce.ecommerceweb.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/order")
public class AdminOrderDetailsServlet extends HttpServlet {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            String idParam = request.getParameter("id");

            if (idParam == null) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID manquant");
                return;
            }

            Long id = Long.parseLong(idParam);

            // ✅ FETCH JOIN SANS ALIAS (JPA compliant)
            Order order = em.createQuery(
                            """
                            SELECT DISTINCT o
                            FROM Order o
                            LEFT JOIN FETCH o.items
                            LEFT JOIN FETCH o.items.product
                            WHERE o.id = :id
                            """,
                            Order.class
                    )
                    .setParameter("id", id)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (order == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Commande introuvable");
                return;
            }

            request.setAttribute("order", order);
            request.getRequestDispatcher("/admin/order-details.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
