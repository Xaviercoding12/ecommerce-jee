<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="java.util.List" %>
<%@ page import="com.example.ecommerce.ecommerceweb.entity.Order" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/orders.css">

<%
    List<Order> orders = (List<Order>) request.getAttribute("orders");
%>

<div class="container">
    <h2>Mes commandes</h2>

    <table>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Total</th>
            <th>Statut</th>
        </tr>

        <% if (orders != null && !orders.isEmpty()) {
            for (Order order : orders) { %>
        <tr>
            <td><%= order.getId() %></td>
            <td><%= order.getOrderDate() %></td>
            <td><%= order.getTotal() %> MAD</td>
            <td><span class="status status-<%= order.getStatus().name().toLowerCase() %>">
        <%= order.getStatus() %>
    </span></td>
        </tr>
        <% }} else { %>
        <tr>
            <td colspan="4">Aucune commande trouvée.</td>
        </tr>
        <% } %>
    </table>
</div>
