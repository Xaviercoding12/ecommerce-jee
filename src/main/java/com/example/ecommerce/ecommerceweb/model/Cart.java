package com.example.ecommerce.ecommerceweb.model;

import com.example.ecommerce.ecommerceweb.entity.Product;

import java.util.*;

public class Cart {

    private Map<Long, CartItem> items = new HashMap<>();

    public void addProduct(Product product) {
        CartItem item = items.get(product.getId());

        if (item == null) {
            items.put(product.getId(), new CartItem(product, 1));
        } else {
            item.setQuantity(item.getQuantity() + 1);
        }
    }

    public void removeProduct(Long productId) {
        items.remove(productId);
    }

    public void updateQuantity(Long productId, int quantity) {
        if (items.containsKey(productId)) {
            if (quantity <= 0) {
                items.remove(productId);
            } else {
                items.get(productId).setQuantity(quantity);
            }
        }
    }

    public Collection<CartItem> getItems() {
        return items.values();
    }

    public double getTotal() {
        return items.values()
                .stream()
                .mapToDouble(CartItem::getSubtotal)
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }
}
