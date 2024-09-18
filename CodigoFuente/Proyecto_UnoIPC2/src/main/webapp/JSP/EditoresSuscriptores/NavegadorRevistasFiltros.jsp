<%-- 
    Document   : NavegadorRevistasFiltros
    Created on : 16 sept 2024, 8:18:26
    Author     : kevin-mushin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
            <div class="d-grid">
                <button type="button" class="btn btn-primary" onclick="history.back()">Regresar</button>
            </div>

    <div class="container mt-5">
            <h1 class="mb-4">Navega por Categorías y Etiquetas</h1>
                    <p>Utiliza los filtros a continuación para encontrar contenido específico. Puedes seleccionar una categoría, múltiples etiquetas, o ambos para refinar tu búsqueda.</p>

                    <form action="${pageContext.request.contextPath}/NavegadorRevistasServlet" method="get" id="formFiltros">
                        <c:if test="${not empty categorias}">
                            <div class="form-group">
                                <label for="categoria">Selecciona una Categoría:</label>
                                <select class="form-control" name="idCategoriaEscogida" id="categoria">
                                    <option value="">Ninguna</option>
                                        <c:forEach var="categoria" items="${categorias}">
                                        <option value="${categoria.idCategoria}">${categoria.nombreCategoria}</option>
                                        </c:forEach>
                                </select>
                            </div>
                    <!-- seleccion de etiquetaas con select -->
                        <div class="form-group">
                                <label for="etiquetas"><strong>Filtrar por Etiquetas:</strong></label>
                                <select class="form-control select2" name="etiquetasEscogidas" multiple="multiple" id="etiquetas">
                                    <c:forEach var="categoria" items="${categorias}">
                                            <c:if test="${not empty categoria.etiquetas}">
                                                <c:forEach var="etiqueta" items="${categoria.etiquetas}">
                                                    <option value="${etiqueta.idEtiqueta}">${etiqueta.etiqueta}</option>
                                                </c:forEach>
                                            </c:if>
                                    </c:forEach>
                                </select>
                        </div>
                        </c:if>

                        <c:if test="${empty categorias}">
                                <p>No hay categorías disponibles.</p>
                        </c:if>

                        <div class="form-group mt-4">
                            <input type="hidden" name="nombreUsuario" value="${usuario.nombreUsuario}">
                            <button type="submit" class="btn btn-primary">Buscar</button>
                        </div>
                    </form>
        </div>
        <div class="container mt-5">
                <c:if test="${not empty revistasEncontradas}">
        <h3 class="mb-4">Resultados para la búsqueda: ${fn:length(revistasEncontradas)} Revistas</h3>
        <div class="row">
            <c:forEach var="revista" items="${revistasEncontradas}">
                <div class="col-md-4">
                    <div class="card mb-4 shadow-sm">
                        <img src="${pageContext.request.contextPath}/includes/PORTADAREVISTA.webp" 
                             class="card-img-top img-fluid" alt="Editorial Mosquito">
                                <div class="card-body text-center">
                                    <h5 class="card-title">${revista.tituloRevista}</h5>

                                    <p class="card-text">Autor: ${revista.nombreAutor}</p>
                                    <p class="card-text">ID: ${revista.idRevista}</p>
                                    
                                    <form action="${pageContext.request.contextPath}/EditorRevistaServlet" method="get">
                                         <input type="hidden" name="accion" value="visualizarRevista">
                                        <input type="hidden" name="idRevistaActualizar" value="${revista.idRevista}">
                                        <button type="submit" class="btn btn-primary w-100"> Previsualizar</button>
                                    </form>
                                    <br>
                                    <c:if test="${revista.aceptaSuscripciones == 'True'}">
                                    <form action="${pageContext.request.contextPath}/JSP/Suscriptores/SuscripcionRevista.jsp">
                                            <input type="hidden" name="tituloRevista" value="${revista.tituloRevista}">
                                            <input type="hidden" name="idRevista" value="${revista.idRevista}">
                                            <input type="hidden" name="nombreUsuario" value="${usuario.nombreUsuario}">
                                            
                                            <button type="submit" class="btn btn-primary w-100"> Suscribirse </button>
                                    </form>
                                    </c:if>
                                </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>
            
    <c:if test="${empty revistasEncontradas}">
        <p>No se encontraron revistas.</p>
    </c:if>
</div>

        <script>
                $(document).ready(function() {
                    $('.select2').select2({
                        placeholder: "Selecciona etiquetas...",
                        allowClear: true
                        });
                    });
        </script>
</body>
</html>

