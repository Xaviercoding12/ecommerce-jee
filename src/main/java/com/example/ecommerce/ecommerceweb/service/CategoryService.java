package com.example.ecommerce.ecommerceweb.service;

import com.example.ecommerce.ecommerceweb.entity.Category;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CategoryService {

    @PersistenceContext(unitName = "EcommercePU")
    private EntityManager em;

    // 🔹 Création sécurisée
    public Category createIfNotExists(String name) {

        TypedQuery<Category> query = em.createQuery(
                "SELECT c FROM Category c WHERE c.name = :name",
                Category.class
        );
        query.setParameter("name", name);

        List<Category> results = query.getResultList();

        if (!results.isEmpty()) {
            return results.get(0); // catégorie déjà existante
        }

        Category category = new Category();
        category.setName(name);
        em.persist(category);
        return category;
    }

    public Category findById(Long id) {
        return em.find(Category.class, id);
    }
}
