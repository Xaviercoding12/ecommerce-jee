package com.example.ecommerce.ecommerceweb.filter;

import com.example.ecommerce.ecommerceweb.entity.Role;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter({
        "/user/cart.jsp",
        "/user/checkout",
        "/user/orders.jsp",
        "/admin/*"
})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);

        boolean loggedIn = (session != null && session.getAttribute("user") != null);
        boolean isAdminPath = req.getRequestURI().contains("/admin");

        if (!loggedIn) {
            res.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        if (isAdminPath) {
            Role role = (Role) session.getAttribute("role");
            if (role != Role.ADMIN) {
                res.sendRedirect(req.getContextPath() + "/user/home.jsp");
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
