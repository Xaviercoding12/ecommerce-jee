<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/login.css">

<div class="auth-container">

    <h2>Connexion</h2>

    <form action="login" method="post">

        <input type="email"
               name="email"
               placeholder="Adresse email"
               required>

        <input type="password"
               name="password"
               placeholder="Mot de passe"
               required>

        <button class="btn btn-primary" type="submit">
            Se connecter
        </button>
    </form>

    <%-- Message d'erreur --%>
    <c:if test="${not empty error}">
        <p class="error-message">${error}</p>
    </c:if>

</div>
