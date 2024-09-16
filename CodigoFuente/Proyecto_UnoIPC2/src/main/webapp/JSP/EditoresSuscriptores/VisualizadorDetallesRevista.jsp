<%-- 
    Document   : VisualizadorDetallesRevista
    Created on : 13 sept 2024, 18:06:49
    Author     : kevin-mushin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@page import="com.mycompany.proyecto_unoipc2.backend.Modelos.Categoria"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.io.Console"%>
<%@page import="com.mycompany.proyecto_unoipc2.backend.Modelos.Revista"%>
<%@page import="com.mycompany.proyecto_unoipc2.backend.Modelos.Revista"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Revista revistaActualizar = (Revista) request.getAttribute("revistaVisualizar");
    
%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Revistas</title>
      <jsp:include page="/includes/resources.jsp"/>

    </head>
            <c:if test="${not empty mensajeResultado}">
                    <div class="alert alert-success alert-dismissible fade show" role="alert">
                        ${mensajeResultado}
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
            </c:if>

    <body class="bg-ligth">
            <div class="container mt-5">
                
                <c:set var="categoriaRevistaVisualizar" value="${null}" />

                <c:if test="${not empty categorias}">
                    <c:forEach var="categoria" items="${categorias}">
                        <c:if test="${categoria.idCategoria == revistaVisualizar.idCategoria}">
                            <c:set var="categoriaRevistaVisualizar" value="${categoria}" />
                        </c:if>
                    </c:forEach>
                </c:if>
                
                <div class="d-grid" >
                          <button type="button" class="btn btn-primary" onclick="history.back()">Regresar</button>
                </div>
                
                <c:if test="${not empty revistaVisualizar}">
                    
                    <div class="card p-4 shadow-sm border-0">
                        <h5 class="text-center mb-4">Detalles sobre la revista: ${revistaVisualizar.tituloRevista}</h5>
                        <p><strong>Fecha Publicación:</strong> <span>${revistaVisualizar.fechaCreacion}</span></p>
                        <p><strong>Autor:</strong> <span>${revistaVisualizar.nombreAutor}</span></p>
                        <p><strong>Título Revista:</strong> <span>${revistaVisualizar.tituloRevista}</span></p>
                        <p><strong>Estado de la revista:</strong> <span>${revistaVisualizar.estadoRevista}</span></p>
                        <p><strong>Numero likes recibidos:</strong> <span>${revistaVisualizar.numeroLikes}</span></p>

                        <c:if test="${not empty categoriaRevistaVisualizar}">
                            <p><strong>Categoría:</strong> <span>${categoriaRevistaVisualizar.nombreCategoria}</span></p>
                            <p><strong>Etiquetas:</strong></p>
                            <ul class="list-unstyled">
                                <c:forEach var="etiqueta" items="${categoriaRevistaVisualizar.etiquetas}">
                                            <li class="badge badge-light text-dark mr-1">${etiqueta.etiqueta}</li>
                                </c:forEach>
                            </ul>
                        </c:if>

                        <c:if test="${not empty usuario}">
                            <c:if test="${usuario.rol == 'EDITOR'}">
                                <h5 class="mt-4">Operaciones sobre Revista</h5>
                                
                                <form id="revistaForm" action="${pageContext.request.contextPath}/EditorRevistaServlet" method="post" >
                                    
                                    <input type="hidden" id="accion" name="accion" value="actualizarRevista"> 
                                    <input type="hidden"  id="idRevista" name="idRevista" value="${revistaVisualizar.idRevista}"> 
                                    <input type="hidden"  id="operacionActualizacion" name="operacionActualizacion" value="actualizarEstados"> 
                                    
                                    <div class="form-group">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="comentable" name="revistaComentable" value="true"
                                                    <c:if test="${revistaVisualizar.revistaComentable}">
                                                        checked
                                                    </c:if>>
                                                <label class="custom-control-label" for="comentable">¿Permitir Comentarios?</label>
                                            </div>
                                            <div class="custom-control custom-checkbox mb-2">
                                                <input type="checkbox" class="custom-control-input" id="likeable" name="revistaLikeable" value="true"
                                                    <c:if test="${revistaVisualizar.revistaLikeable}">
                                                        checked
                                                    </c:if>>
                                                <label class="custom-control-label" for="likeable">¿Permitir me gusta?</label>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-primary">Actualizar</button>
                                    </form>
                                        
                            </c:if>
                        </c:if>
                    </div>
                </c:if>
            </div>

    </body>
</html>

        