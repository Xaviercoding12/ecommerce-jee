package com.example.ecommerce.ecommerceweb.service;

import com.example.ecommerce.ecommerceweb.entity.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class OrderService {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    public void saveOrder(Order order) {
        em.persist(order);
    }

    public void updateStatus(Long orderId, OrderStatus status) {
        Order order = em.find(Order.class, orderId);
        if (order != null) {
            order.setStatus(status);
        }
    }
}
