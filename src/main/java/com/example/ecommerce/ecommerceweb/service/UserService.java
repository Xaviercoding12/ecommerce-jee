package com.example.ecommerce.ecommerceweb.service;

import com.example.ecommerce.ecommerceweb.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    // 🔹 Création utilisateur
    public void create(User user) {
        em.persist(user);
    }

    // 🔹 Recherche par ID
    public User findById(Long id) {
        return em.find(User.class, id);
    }
}
