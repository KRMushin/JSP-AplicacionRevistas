<%-- 
    Document   : PaginaPrincipal
    Created on : 8 sept 2024, 10:40:42
    Author     : kevin-mushin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
//response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
//response.setHeader("Pragma", "no-cache");
//response.setDateHeader("Expires", 0);

// Verificar si hay una sesión activa
if (session == null || session.getAttribute("usuario") == null) {
    response.sendRedirect("login.jsp");
    return;
}
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Página Principal</title>
    <link rel="stylesheet" href="styles.css">
     <script src="${pageContext.request.contextPath}/js/Script.js"></script>

</head>
<body>

        <!-- Menú de navegación dinámico -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <c:forEach var="opcion" items="${menuOpciones}">
                        <a class="nav-item" href="${pageContext.request.contextPath}/${opcion.opcionAccion}">${opcion.opcionNombre}</a>
                </c:forEach>
            </ul>
        </div>
    </nav>

    <!-- Información del usuario -->
    <div class="container mt-4">
        <div class="alert alert-info" role="alert">
            Bienvenido: <strong>${usuario.nombreUsuario}</strong> | Rol: <strong>${usuario.rol} </strong>
        </div>

        <!-- Contenido dinámico -->
        <div id="contenido">
            <!-- Aquí puedes incluir el contenido dinámico basado en las opciones seleccionadas -->
        </div>

        <!-- Botón de Cerrar Sesión -->
        <button class="btn btn-danger mt-3" onclick="window.location.href='Logout'">Cerrar Sesión</button>
    </div>

    <!-- Incluye Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>

