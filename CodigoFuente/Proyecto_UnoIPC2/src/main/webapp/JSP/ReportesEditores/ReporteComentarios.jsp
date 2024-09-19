<%-- 
    Document   : ReporteComentarios
    Created on : 18 sept 2024, 22:10:43
    Author     : kevin-mushin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
    <div class="container mt-5">
        <h1 class="text-center mb-4">Filtrar Comentarios</h1>

        <!-- Filtro por Revista -->
        <c:if test="${not empty revistasDisponibles}">
            <form action="${pageContext.request.contextPath}/ReporteComentariosServlet" method="POST" class="mb-4">
                    <div class="mb-3">
                        
                        <<input type="hidden" id="accion" name="accion" value="filtrarRevista">
                        
            <label for="revista" class="form-label">Filtrar por Revista</label>
                        <select class="form-select" id="idrevista" name="idRevista">
                            <option value="">Selecciona una revista</option>
                            <c:forEach var="revista" items="${revistasDisponibles}">
                                <!-- El valor será el idRevista en lugar del título -->
                                <option value="${revista.idRevista}">${revista.tituloRevista}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Filtrar por Revista</button>
                </form>
        </c:if>

        <hr>
        <!-- Filtro por Intervalo de Fechas -->
        <form action="${pageContext.request.contextPath}/ReporteComentariosServlet" method="POST" class="mb-4">
            
            <<input type="hidden" id="accion" name="accion" value="filtrarFechas">
            <div class="mb-3">
                <label for="fechaInicio" class="form-label">Fecha Inicio</label>
                <input type="date" class="form-control" id="fechaInicio" name="fechaInicio">
            </div>

            <div class="mb-3">
                <label for="fechaFin" class="form-label">Fecha Fin</label>
                <input type="date" class="form-control" id="fechaFin" name="fechaFin">
            </div>
            
            <button type="submit" class="btn btn-primary">Filtrar por Fechas</button>
        </form>

        <!-- Lista de comentarios de ejemplo -->
        <c:if test="${not empty comentariosFiltrados}">
                <h2 class="mt-5">Comentarios</h2>
                <div class="row">
                    <c:forEach var="comentario" items="${comentariosFiltrados}">
                        <div class="col-md-4 mb-4">
                            <div class="card">
                                <div class="card-body">
                                    <h5 class="card-title">Autor Comentario: ${comentario.nombreUsuario}</h5>
                                    <h5 class="card-title">Revista: ${comentario.tituloRevista}</h5>
                                    <p class="card-text">Fecha: ${comentario.fechaComentario}</p>
                                    <p class="card-text">Comentario: ${comentario.comentario}</p>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
        </c:if>
                <form action="${pageContext.request.contextPath}/GeneradorReporteServlet" method="GET" target="_blank">

                    <input type="hidden" name="generarReporte" value="generarReporte">
                    <button type="submit" class="btn btn-secondary">Generar Reporte</button>
            </form>


        <!-- Botón de regresar -->
        <div class="d-grid gap-2 d-md-flex justify-content-md-end mt-4">
            <button onclick="history.back()" class="btn btn-warning">Regresar</button>
        </div>
    </div>

</body>
</html>
