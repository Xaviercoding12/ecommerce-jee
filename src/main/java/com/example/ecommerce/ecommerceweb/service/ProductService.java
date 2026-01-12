package com.example.ecommerce.ecommerceweb.service;

import com.example.ecommerce.ecommerceweb.entity.Product;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class ProductService {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    public void create(Product product) {
        em.persist(product);
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }
}
