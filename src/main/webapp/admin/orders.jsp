<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.ecommerce.ecommerceweb.entity.Order" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/admin-orders.css">

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
%>

<div class="container">
    <h2>📦 Gestion des commandes</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Client</th>
            <th>Total</th>
            <th>Statut</th>
            <th>Action</th>
        </tr>

        <% if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) { %>

        <tr>
            <td>#<%= order.getId() %></td>
            <td><%= order.getOrderDate() %></td>
            <td><%= order.getUser().getEmail() %></td>
            <td><%= order.getTotal() %> MAD</td>

            <td>
                <span class="status status-<%= order.getStatus().name().toLowerCase() %>">
                    <%= order.getStatus() %>
                </span>
            </td>

            <td>
                <a class="btn btn-primary"
                   href="<%= request.getContextPath() %>/admin/order?id=<%= order.getId() %>">
                    Voir
                </a>
            </td>
        </tr>

        <% }} else { %>
        <tr>
            <td colspan="6">Aucune commande.</td>
        </tr>
        <% } %>
    </table>
</div>
