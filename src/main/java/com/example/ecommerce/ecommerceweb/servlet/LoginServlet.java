package com.example.ecommerce.ecommerceweb.servlet;

import com.example.ecommerce.ecommerceweb.dao.UserDAO;
import com.example.ecommerce.ecommerceweb.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDAO = new UserDAO(em);
        User user = userDAO.findByEmailAndPassword(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

            if (user.getRole().name().equals("ADMIN")) {
                response.sendRedirect("admin/dashboard.jsp");
            } else {
                response.sendRedirect(request.getContextPath() + "/home");

            }
        } else {
            request.setAttribute("error", "Email ou mot de passe incorrect");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
