package com.example.ecommerce.ecommerceweb.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/test-jpa")
public class TestJPAServlet extends HttpServlet {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager entityManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        try {
            entityManager.createNativeQuery("SELECT 1").getSingleResult();
            resp.getWriter().println("Connexion JPA + WildFly OK ✅");
        } catch (Exception e) {
            resp.getWriter().println("Erreur JPA ❌");
            e.printStackTrace(resp.getWriter());
        }
    }
}
