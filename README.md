Ce projet est une application web e-commerce complète développée avec Jakarta EE (JEE).
Il permet aux utilisateurs de consulter des produits, gérer un panier, passer des commandes et suivre leur historique, et aux administrateurs de gérer les commandes via un espace dédié.

Objectifs du projet :

Mettre en pratique Jakarta EE dans un cas réel
Implémenter un workflow e-commerce complet
Respecter une architecture claire (MVC + Services)
Gérer l’authentification et les rôles (USER / ADMIN)
Manipuler JPA + Hibernate avec transactions JTA
Créer une interface moderne et responsive

Architecture
📁 Backend (Java)

src/main/java
└── com.example.ecommerce.ecommerceweb
    ├── entity        # Entités JPA
    ├── dao           # Accès aux données
    ├── service       # Logique métier
    ├── servlet       # Contrôleurs HTTP
    │   └── admin     # Servlets Admin
    ├── filter        # Filtres de sécurité
    ├── model         # Panier & objets métier
    └── util          # Utilitaires

  📁 Frontend (JSP)

  src/main/webapp
├── assets/css        # Styles CSS
├── admin             # Pages Admin
├── user              # Pages Utilisateur
├── index.jsp
├── login.jsp
└── WEB-INF


Modèle de données :

 User:
id
name
email
password
role (USER, ADMIN)

 Product:
id
name
price

 Order:
id
orderDate
status (PENDING, PAID, CANCELLED)
total
user
items

 OrderItem:
id
product
quantity
priceAtPurchase
order

 Cart (Session):
Liste de produits
Gestion des quantités
Calcul automatique du total


Sécurité :
Authentification:
Connexion via LoginServlet
Utilisateur stocké en session
Déconnexion via LogoutServlet

Autorisation:

AuthFilter :
Protège /user/* et /admin/*
Vérifie la connexion
Vérifie le rôle ADMIN pour /admin/*

 Fonctionnalités Utilisateur:

 _Consultation des produits

 _Ajout au panier

 _Mise à jour des quantités

 _Suppression d’articles

 _Passage de commande

  _Historique des commandes

 _Suivi du statut des commandes

Pages clés :

/ → Accueil

/user/home → Produits

/user/cart → Panier

/user/orders → Mes commandes

 Fonctionnalités Administrateur :

 _Tableau de bord admin

 _Liste des commandes

 _Détails d’une commande

 _Modification du statut (PENDING / PAID / CANCELLED)

Pages admin :

/admin/dashboard

/admin/orders

/admin/order?id=...
