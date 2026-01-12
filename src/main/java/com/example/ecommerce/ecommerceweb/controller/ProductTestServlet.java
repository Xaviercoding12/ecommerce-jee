package com.example.ecommerce.ecommerceweb.controller;

import com.example.ecommerce.ecommerceweb.entity.Category;
import com.example.ecommerce.ecommerceweb.entity.Product;
import com.example.ecommerce.ecommerceweb.service.CategoryService;
import com.example.ecommerce.ecommerceweb.service.ProductService;
import jakarta.inject.Inject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test-product")
public class ProductTestServlet extends HttpServlet {

    @Inject
    private ProductService productService;

    @Inject
    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {

        try {
            Category category = categoryService.createIfNotExists("Electronics");

            Product product = new Product();
            product.setName("Laptop HP");
            product.setPrice(9500);
            product.setStock(10);
            product.setCategory(category);

            productService.create(product);

            resp.getWriter().println("Produit créé avec succès ✅");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
