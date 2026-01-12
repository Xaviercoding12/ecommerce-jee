<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.ecommerce.ecommerceweb.entity.*" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/details.css">

<%
    Order order = (Order) request.getAttribute("order");

    if (order == null) {
%>
<div class="container">
    <h2>Commande introuvable</h2>
    <a href="orders" class="btn btn-primary">← Retour</a>
</div>
<%
        return;
    }
%>

<div class="container admin-order-details">

    <div class="order-header">
        <h2>📦 Commande #<%= order.getId() %></h2>
        <span class="status-badge status-<%= order.getStatus().name().toLowerCase() %>">
            <%= order.getStatus() %>
        </span>
    </div>

    <div class="order-info">
        <div>
            <strong>Client</strong>
            <span><%= order.getUser().getEmail() %></span>
        </div>
        <div>
            <strong>Date</strong>
            <span><%= order.getOrderDate() %></span>
        </div>
        <div>
            <strong>Total</strong>
            <span class="price"><%= order.getTotal() %> MAD</span>
        </div>
    </div>

    <h3 class="section-title">🛒 Produits commandés</h3>

    <table class="admin-table">
        <thead>
        <tr>
            <th>Produit</th>
            <th>Quantité</th>
            <th>Prix unitaire</th>
        </tr>
        </thead>
        <tbody>
        <% for (OrderItem item : order.getItems()) { %>
        <tr>
            <td><%= item.getProduct().getName() %></td>
            <td>x<%= item.getQuantity() %></td>
            <td><%= item.getPriceAtPurchase() %> MAD</td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <h3 class="section-title">⚙️ Modifier le statut</h3>

    <form class="status-form"
          method="post"
          action="<%= request.getContextPath() %>/admin/update-order-status">

        <input type="hidden" name="orderId" value="<%= order.getId() %>">

        <select name="status">
            <% for (OrderStatus s : OrderStatus.values()) { %>
            <option value="<%= s.name() %>"
                    <%= s == order.getStatus() ? "selected" : "" %>>
                <%= s.name() %>
            </option>
            <% } %>
        </select>

        <button class="btn btn-success" type="submit">
            ✔ Mettre à jour
        </button>
    </form>

    <div class="actions">
        <a href="orders" class="btn btn-primary">← Retour aux commandes</a>
    </div>

</div>
