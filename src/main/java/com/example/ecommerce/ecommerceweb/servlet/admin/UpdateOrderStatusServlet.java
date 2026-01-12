package com.example.ecommerce.ecommerceweb.servlet.admin;

import com.example.ecommerce.ecommerceweb.entity.OrderStatus;
import com.example.ecommerce.ecommerceweb.service.OrderService;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin/update-order-status")
public class UpdateOrderStatusServlet extends HttpServlet {

    @Inject
    private OrderService orderService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Long orderId = Long.parseLong(request.getParameter("orderId"));
        OrderStatus status = OrderStatus.valueOf(request.getParameter("status"));

        orderService.updateStatus(orderId, status);

        response.sendRedirect(request.getContextPath() + "/admin/orders");
    }
}
