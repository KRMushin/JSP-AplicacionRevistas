<%-- 
    Document   : SuscripcionRevista
    Created on : 16 sept 2024, 17:59:03
    Author     : kevin-mushin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Suscripcion a revistas</title>
      <jsp:include page="/includes/resources.jsp"/>

    </head>
<body>
           <c:if test="${not empty mensajeServicio}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        ${mensajeServicio}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
            </c:if>
    
        <div class="container mt-5">
            <div class="d-grid">
                <button type="button" class="btn btn-primary" onclick="history.back()">Regresar</button>
            </div>
            <br>3
            <h3>Confirmar Suscripcion</h3>
            <!-- Mostrar el título de la revista -->
            <h4>Titulo de revista: ${param.tituloRevista}</h4>
            <c:out value="${param.tituloRevista}" />

            <form method="post" action="${pageContext.request.contextPath}/SuscripcionesServlet">
                 <input type="hidden" name="nombreSuscriptor" value="${usuario.nombreUsuario}">
                <input type="hidden" name="idRevista" value="${param.idRevista}">

                    <div class="mb-3">
                        <label for="fechaSuscripcion" class="form-label">Fecha de Suscripción</label>
                        <input type="date" class="form-control" id="fechaCreacion" name="fechaCreacion" required>
                    </div>
                <button type="submit" class="btn btn-primary">Confirmar Suscripcion</button>
            </form>
                    
        </div>
    </body>
</html>
