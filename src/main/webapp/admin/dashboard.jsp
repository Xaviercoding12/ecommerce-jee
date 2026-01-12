<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/admin.css">

<div class="admin-navbar">
    <div class="brand">🛠 Admin Panel</div>
    <a href="<%= request.getContextPath() %>/logout" class="btn btn-danger">
        Déconnexion
    </a>
</div>

<div class="container">
    <h2 class="page-title">Dashboard Administrateur</h2>

    <div class="admin-grid">
        <div class="admin-card">
            <h3>📦 Commandes</h3>
            <p>Gérer toutes les commandes clients</p>
            <a href="<%= request.getContextPath() %>/admin/orders"
               class="btn btn-primary">
                Accéder
            </a>
        </div>

        <div class="admin-card disabled">
            <h3>🛍 Produits</h3>
            <p>Bientôt disponible</p>
            <button class="btn btn-secondary" disabled>
                En cours
            </button>
        </div>

        <div class="admin-card disabled">
            <h3>📊 Statistiques</h3>
            <p>Chiffre d'affaires & tendances</p>
            <button class="btn btn-secondary" disabled>
                En cours
            </button>
        </div>
    </div>
</div>
