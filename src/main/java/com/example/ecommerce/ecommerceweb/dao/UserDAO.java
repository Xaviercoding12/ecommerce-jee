package com.example.ecommerce.ecommerceweb.dao;

import com.example.ecommerce.ecommerceweb.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class UserDAO {

    private EntityManager em;

    public UserDAO(EntityManager em) {
        this.em = em;
    }

    public User findByEmailAndPassword(String email, String password) {
        try {
            return em.createQuery(
                            "SELECT u FROM User u WHERE u.email = :email AND u.password = :password",
                            User.class)
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(User user) {
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
}
