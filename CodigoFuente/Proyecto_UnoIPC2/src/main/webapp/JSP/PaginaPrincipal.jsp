<%-- 
    Document   : PaginaPrincipal
    Created on : 8 sept 2024, 10:40:42
    Author     : kevin-mushin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
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
            <jsp:include page="/includes/resources.jsp"/>

</head>
<body>

        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#">Mi Aplicación</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav ml-auto">
                            <c:forEach var="opcion" items="${menuOpciones}">
                                <li class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/${opcion.opcionAccion}">
                                        ${opcion.opcionNombre}
                                    </a>
                                </li>
                            </c:forEach>
                    </ul>
                </div>
            </nav>


    <div class="container mt-4">
        <div class="alert alert-info" role="alert">
            Bienvenido: <strong>${usuario.nombreUsuario}</strong> | Rol: <strong>${usuario.rol} </strong>
        </div>
        
        <a class="btn btn-danger mt-3" href="LogoutServlet" > Cerrar Sesion</a>

        <div id="contenido">
            <img src="${pageContext.request.contextPath}/includes/EDITMOSQUITO.webp" alt="Descripción de la imagen" class="img-fluid">
        </div>
    </div>
</body>
</html>

