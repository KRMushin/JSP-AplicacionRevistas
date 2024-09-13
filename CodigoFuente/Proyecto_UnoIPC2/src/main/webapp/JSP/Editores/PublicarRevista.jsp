<%-- 
    Document   : PublicarRevista
    Created on : 11 sept 2024, 23:01:11
    Author     : kevin-mushin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.mycompany.proyecto_unoipc2.backend.Modelos.Categoria"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Publicar Revista</title>
    <jsp:include page="/includes/resources.jsp"/>
</head>
<body>
    
              <c:if test="${not empty mensajeExito}">
                        <script>
                            alert("${mensajeExito}");
                            window.location.href = "${pageContext.request.contextPath}/JSP/PaginaPrincipal.jsp"; 
                        </script>
            </c:if>
    
            <c:if test="${not empty mensaje}">
                        <div class="${tipoMensaje == 'success' ? 'alert alert-success' : 'alert alert-danger'}">
                        <c:out value="${mensaje}" />
                        </div>
            </c:if>
    
    
    <div class="container mt-5">
        <div class="card shadow-sm">
                   <div class="d-grid" >
                          <button type="button" class="btn btn-primary" onclick="history.back()">Regresar</button>
                   </div>
            <br>
            <div class="card-header bg-primary text-white">
                <h4 class="mb-0">Formulario de Publicación de Revista</h4>
            </div>

            <div class="card-body">

                <form id="revistaForm" action="${pageContext.request.contextPath}/EditorRevistaServlet" method="post" enctype="multipart/form-data">
                    <div class="mb-3">

                        <input type="hidden" name="accion" id="accion"  value="publicacion">
                        <input type="hidden" name="nombreAutor" id="nombreAutor"  value=${usuario.nombreUsuario}>


                        <label for="tituloRevista" class="form-label">Título de la Revista</label>
                        <input type="text" class="form-control" id="tituloRevista" name="tituloRevista" placeholder="Ingrese el título de la revista" required>
                    </div>

                    <div class="mb-3">
                        <label for="descripcion" class="form-label">Descripción</label>
                        <textarea class="form-control" id="descripcion" name="descripcion" rows="3" placeholder="Ingrese una descripción de la revista" required></textarea>
                    </div>

                    <div class="mb-3">
                        <label for="fechaPublicacion" class="form-label">Fecha de Publicación</label>
                        <input type="date" class="form-control" id="fechaPublicacion" name="fechaPublicacion" required>
                    </div>

                    <div class="mb-3">
                        <label for="idCategoria" class="form-label">Categoría</label>
                        <select class="form-select" id="idCategoria" name="idCategoria" required>
                            <option value="" selected disabled>Seleccione una categoría</option>
                            <!-- Opciones de categorías aquí -->
                            <c:forEach var="categoria" items="${categorias}">
                                <option value="${categoria.idCategoria}">${categoria.nombreCategoria}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="checkboxContainer" class="mb-3">
                        <label class="form-label">Etiquetas</label>
                    </div>

                    <!-- contenedor oculto  -->
                    <div id="hiddenOptions" style="display: none;">
                        <!-- mostrar todas las etiquetas al cargar pero con atributo data-categoria -->
                        <c:forEach var="categoria" items="${categorias}">
                            <c:forEach var="etiquetasCategoria" items="${categoria.etiquetas}">
                                <span class="etiqueta-opcion" data-categoria="${categoria.idCategoria}" data-value="${etiquetasCategoria.idEtiqueta}">
                                    ${etiquetasCategoria.etiqueta}
                                </span>
                            </c:forEach>
                        </c:forEach>
                    </div>


                    <div class="mb-3">
                        <label for="revistaPDF" class="form-label">Subir PDF</label>
                        <div class="input-group">
                            <input type="file" class="form-control" id="revistaPDF" name="revistaPDF" accept=".pdf" required>
                            <div class="input-group-append">
                                <span class="input-group-text">Formato PDF</span>
                            </div>
                        </div>
                        <small class="form-text text-muted">Solo se permiten archivos en formato PDF.</small>
                    </div>

                    <div class="mb-3">
                        <label class="form-label">Opciones Adicionales</label>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="revistaComentable" name="revistaComentable" value="true">
                            <label class="form-check-label" for="revistaComentable">Permitir comentarios</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" id="revistaLikeable" name="revistaLikeable" value="true">
                            <label class="form-check-label" for="revistaLikeable">Permitir "likes"</label>
                        </div>
                    </div>

                    <div class="d-grid">
                        <button type="submit" class="btn btn-primary">Publicar Revista</button> 
                    </div>
                </form>
                     <br>
            </div>
            <div class="card-footer text-muted">
                Todos los campos son obligatorios.
                     <div class="d-grid" >
                        <button type="button" onclick="history.back()">Regresar</button>
                     </div>
            </div>
        </div>
    </div>
    <jsp:include page="/includes/footer.jsp"/>
</body>
</html>

