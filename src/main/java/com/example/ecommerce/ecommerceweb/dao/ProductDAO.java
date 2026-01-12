package com.example.ecommerce.ecommerceweb.dao;

import com.example.ecommerce.ecommerceweb.entity.Product;
import jakarta.persistence.EntityManager;

import java.util.List;

public class ProductDAO {

    private EntityManager em;

    public ProductDAO(EntityManager em) {
        this.em = em;
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> findAll() {
        return em.createQuery("SELECT p FROM Product p", Product.class)
                .getResultList();
    }
}
