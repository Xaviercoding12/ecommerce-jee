<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.example.ecommerce.ecommerceweb.model.Cart" %>
<%@ page import="com.example.ecommerce.ecommerceweb.model.CartItem" %>
<%@ page import="java.util.Collection" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/cart.css">


<%
    Cart cart = (Cart) session.getAttribute("cart");
%>

<div class="container">
    <h2>Mon Panier</h2>

    <% if (cart == null || cart.isEmpty()) { %>
    <p>Votre panier est vide.</p>
    <a href="<%= request.getContextPath() %>/" class="btn btn-primary">
        Continuer les achats
    </a>
    <% } else { %>

    <table>
        <tr>
            <th>Produit</th>
            <th>Qté</th>
            <th>Prix unitaire</th>
            <th>Sous-total</th>
            <th>Action</th>
        </tr>

        <% for (CartItem item : cart.getItems()) { %>
        <tr>
            <td><%= item.getProduct().getName() %></td>

            <td>
                <form action="update-cart" method="post">
                    <input type="hidden" name="productId"
                           value="<%= item.getProduct().getId() %>">
                    <input type="number" name="quantity"
                           value="<%= item.getQuantity() %>" min="1">
                    <button class="btn btn-primary" type="submit">
                        Mettre à jour
                    </button>
                </form>
            </td>

            <td><%= item.getProduct().getPrice() %> MAD</td>
            <td><%= item.getSubtotal() %> MAD</td>

            <td>
                <form action="update-cart" method="post">
                    <input type="hidden" name="productId"
                           value="<%= item.getProduct().getId() %>">
                    <input type="hidden" name="quantity" value="0">
                    <button class="btn btn-danger" type="submit">
                        Supprimer
                    </button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>

    <br>

    <h3>
        Total :
        <span style="color:#22c55e">
            <%= cart.getTotal() %> MAD
        </span>
    </h3>

    <a href="checkout" class="btn btn-success">
        Passer commande
    </a>

    <% } %>
</div>
