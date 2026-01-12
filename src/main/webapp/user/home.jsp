<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ecommerce.ecommerceweb.entity.Product" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">

<div class="container">

    <!-- HEADER PAGE -->
    <div class="shop-header">
        <div>
            <h2>🛍️ Nos Produits</h2>
            <p class="shop-subtitle">
                Découvrez nos meilleurs produits sélectionnés pour vous
            </p>
        </div>

        <!-- BOUTON MES COMMANDES -->
        <a href="<%= request.getContextPath() %>/user/orders"
           class="btn btn-outline">
            📦 Mes commandes
        </a>
    </div>

    <div class="grid">
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            for (Product p : products) {
        %>

        <div class="card product-card">

            <!-- BADGE -->
            <span class="badge-new">Nouveau</span>

            <!-- IMAGE FAKE (placeholder visuel) -->
            <div class="product-image">
                <span>📦</span>
            </div>

            <h3><%= p.getName() %></h3>

            <p class="price">
                <%= p.getPrice() %> <span>MAD</span>
            </p>

            <p class="product-desc">
                Produit de haute qualité, livraison rapide et garantie.
            </p>

            <a href="<%= request.getContextPath() %>/user/add-to-cart?id=<%= p.getId() %>"
               class="btn btn-primary">
                ➕ Ajouter au panier
            </a>
        </div>

        <% } %>
    </div>
</div>
