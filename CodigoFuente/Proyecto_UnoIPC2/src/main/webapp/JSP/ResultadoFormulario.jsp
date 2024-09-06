<%-- 
    Document   : ResultadoFormulario
    Created on : 5 sept 2024, 19:32:52
    Author     : kevin-mushin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado del Registro</title>
</head>
<body>
    <c:choose>
        <!-- Mostrar mensaje de error si existe -->
        <c:when test="${not empty mensajeError}">
            <script>
                alert("${mensajeError}");
            </script>
        </c:when>
        <!-- Mostrar mensaje de éxito si el usuario se creó correctamente -->
        <c:otherwise>
            <h2>${mensaje}</h2>
            <script>
                alert("${mensaje}");
            </script>
        </c:otherwise>
    </c:choose>
</body>
</html>
