<%-- 
    Document   : ComentarRevistas
    Created on : 18 sept 2024, 0:06:13
    Author     : kevin-mushin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> <!-- Importar JSTL -->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comentarios revistas</title>
      <jsp:include page="/includes/resources.jsp"/>
    </head>
<body class="bg-light">
    <c:if test="${not empty respuestaServidor}">
                            <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${respuestaServidor}
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                    </button>
                </div>
    </c:if>
    

        <div class="container mt-5">
               <div class="d-grid">
                <button type="button" class="btn btn-primary" onclick="history.back()">Regresar</button>
            </div>

            <c:if test="${revistaComentable == 'True'}">
            <h1 class="mb-4">Sección de Comentarios</h1>

            <!-- Formulario para insertar un comentario -->
                <form id="formComentario" action="${pageContext.request.contextPath}/ComentariosServlet" method="post">
                        <input type="hidden" id="nombreUsuario" name="nombreUsuario" value="${usuario.nombreUsuario}">
                        <input type="hidden" id="idRevista" name="idRevista" value="${idRevista}">
                        <div class="mb-4">
                            <div class="form-group">
                                <h4>Usuario: ${usuario.nombreUsuario}</h4>
                                
                            </div>
                            <div class="form-group">
                                <label for="comentario">Comentario</label>
                                <textarea name="comentario" class="form-control" id="comentario" rows="3" placeholder="Escribe tu comentario aquí..."></textarea>
                            </div>
                            <div class="form-group">
                                <label for="fecha">Fecha</label>
                                <input name="fechaComentario" type="date" class="form-control" id="fechaComentario">
                            </div>
                            <button class="btn btn-primary">Agregar Comentario</button>
                        </div>
                </form>
           </c:if>
            <c:if test="${revistaComentable != 'True'}">
                    <h3 class="mb-4"> Los comentarios estan desactivados por el autor de la revista ...</h3>
           </c:if>
    <c:if test="${not empty comentariosUsuario}">

            <c:forEach var="comentario" items="${comentariosUsuario}">
                    <!-- Contenedor para mostrar los comentarios -->
                    <div class="border rounded p-3 bg-white" style="max-height: 300px; overflow-y: auto;">
                            <!-- asignacion a la tarjeta por cada comentario-->
                            <div class="card mb-3">
                                <div class="card-body">
                                    <h5 class="card-title">Usuario: ${comentario.nombreUsuario}</h5>
                                    <p class="card-text">${comentario.comentario}</p>
                                    <small class="text-muted">Fecha: ${comentario.fechaComentario}</small>
                                </div>
                            </div>
                    </div>
            </c:forEach>
    </c:if>
    <c:if test="${empty comentariosUsuario}">
        <h3 class="mb-4">Aun no haces comentarios a esta revista</h3>
    </c:if>
    <!-- boton para regresar en la parte inferior -->
    <a href="#" class="btn btn-secondary mt-3">Regresar</a>
</div>
</body>
</html>
