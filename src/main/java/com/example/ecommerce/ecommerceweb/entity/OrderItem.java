package com.example.ecommerce.ecommerceweb.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔗 Lien vers Order
    @ManyToOne(optional = false)
    @JoinColumn(name = "order_id")
    private Order order;

    // 🔗 Lien vers Product
    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    // ⭐ IMPORTANT : correspond EXACTEMENT à la colonne SQL
    @Column(name = "priceatpurchase", nullable = false)
    private double priceAtPurchase;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public double getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(double priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }
}
