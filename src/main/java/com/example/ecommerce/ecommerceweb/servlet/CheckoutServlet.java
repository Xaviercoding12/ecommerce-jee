package com.example.ecommerce.ecommerceweb.servlet;

import com.example.ecommerce.ecommerceweb.entity.*;
import com.example.ecommerce.ecommerceweb.model.Cart;
import com.example.ecommerce.ecommerceweb.model.CartItem;
import com.example.ecommerce.ecommerceweb.service.OrderService;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/user/checkout")
public class CheckoutServlet extends HttpServlet {

    @Inject
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDING);
        order.setUser(user);
        order.setTotal(cart.getTotal());

        List<OrderItem> items = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());

            // ⭐ CRUCIAL : jamais NULL
            item.setPriceAtPurchase(cartItem.getProduct().getPrice());

            items.add(item);
        }

        order.setItems(items);

        orderService.saveOrder(order);

        cart.clear();
        session.setAttribute("cart", cart);

        response.sendRedirect(request.getContextPath() + "/user/orders");

    }
}
