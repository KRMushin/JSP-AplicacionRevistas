<%-- 
    Document   : PerfilUsuario
    Created on : 8 sept 2024, 17:28:05
    Author     : kevin-mushin
--%>

<%@page import="com.mycompany.proyecto_unoipc2.backend.Modelos.FotoUsuario"%>
<%@page import="com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Perfil de Usuario</title>
    <script src="${pageContext.request.contextPath}/JS/PerfilUsuario.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Styles.css">
            <jsp:include page="/includes/resources.jsp"/>
</head>
<body class="container mt-5">

    <!-- Mensaje de éxito/error -->
    <c:if test="${not empty mensaje}">
        <div class="alert <c:out value='${tipoMensaje == "success" ? "alert-success" : "alert-danger"}' />" role="alert">
            <c:out value="${mensaje}" />
        </div>
    </c:if>

    <!-- Sección de perfil de usuario -->
    <div class="row align-items-center mb-4">
        <div class="col-auto">
            <button type="button" class="btn btn-secondary" onclick="history.back()">Regresar</button>
        </div>
        <div class="col">
            <h2>Perfil de: <strong>${usuarioPerfil.nombreUsuario}</strong></h2>
        </div>
        <div class="col-auto">
            <c:choose>
                <c:when test="${usuarioPerfil.foto != null && usuarioPerfil.foto.idFoto > 0}">
                    <img class="img-thumbnail" src="${pageContext.request.contextPath}/PerfilUsuarioServlet?action=mostrarImagen&idFoto=${usuarioPerfil.foto.idFoto}" alt="Foto de Perfil" />
                </c:when>
                <c:otherwise>
                    <p class="text-muted">Sin foto disponible</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <!-- Información del usuario -->
    <div class="alert alert-info d-flex justify-content-between align-items-center" role="alert">
        <span>Usuario: <strong>${usuarioPerfil.nombreUsuario}</strong></span>
        <span>Rol: <strong>${usuarioPerfil.rol}</strong></span>
    </div>

    <!-- Sección del formulario -->
    <form action="${pageContext.request.contextPath}/PerfilUsuarioServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="nombreUsuario" value="${usuarioPerfil.nombreUsuario}" />
        <input type="hidden" name="idFoto" value="${usuarioPerfil.foto.idFoto}" />

        <!-- Foto de perfil -->
        <div class="form-group">
            <label for="foto">Actualizar Foto Perfil</label>
            <input type="file" class="form-control-file" name="foto" accept="image/*" 
                <c:if test="${not isEditable}">disabled</c:if> />
        </div>

        <!-- Descripción -->
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <textarea class="form-control" id="descripcion" name="descripcion" rows="3" 
                <c:if test="${not isEditable}">readonly</c:if>>
                <c:out value="${usuarioPerfil.descripcion != null ? usuarioPerfil.descripcion : 'Agrega una descripción...'}" />
            </textarea>
        </div>

        <!-- Nombre de pila -->
        <div class="form-group">
            <label for="nombreUsuarioInput">Nombre:</label>
            <input type="text" class="form-control" id="nombreUsuarioInput" name="nombre" value="${usuarioPerfil.nombre}" 
                <c:if test="${not isEditable}">readonly</c:if> />
        </div>

        <!-- Preferencias (Temas de Preferencia) -->
        <div class="form-group">
            <h3>Temas de Preferencia</h3>
            <c:forEach var="tema" items="${fn:split('Terror,Arte,Ciencia,Tecnologia', ',')}">
                <c:set var="checked" value="false" />
                <c:forEach var="preferencia" items="${usuarioPerfil.preferencias}">
                    <c:if test="${preferencia.tipoPreferencia == 'TEMA_PREFERENCIA' && preferencia.preferencia == tema}">
                        <c:set var="checked" value="true" />
                    </c:if>
                </c:forEach>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="${tema}" name="preferenciasTemas" value="${tema}" 
                        <c:if test="${checked == 'true'}">checked="checked"</c:if> 
                        <c:if test="${not isEditable}">disabled</c:if> />
                    <label class="form-check-label" for="${tema}">${tema}</label>
                </div>
            </c:forEach>
        </div>

        <!-- Hobbies -->
        <div class="form-group">
            <h3>Hobbies</h3>
            <c:forEach var="hobby" items="${fn:split('Deportes,Lectura,Música', ',')}">
                <c:set var="checked" value="false" />
                <c:forEach var="preferencia" items="${usuarioPerfil.preferencias}">
                    <c:if test="${preferencia.tipoPreferencia == 'HOBBIE' && preferencia.preferencia == hobby}">
                        <c:set var="checked" value="true" />
                    </c:if>
                </c:forEach>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="hobbies_${hobby}" name="preferenciasHobbies" value="${hobby}" 
                        <c:if test="${checked == 'true'}">checked="checked"</c:if> 
                        <c:if test="${not isEditable}">disabled</c:if> />
                    <label class="form-check-label" for="hobbies_${hobby}">${hobby}</label>
                </div>
            </c:forEach>
        </div>

        <!-- Gustos -->
        <div class="form-group">
            <h3>Gustos</h3>
            <c:forEach var="gusto" items="${fn:split('Cine,Viajar,Cocina', ',')}">
                <c:set var="checked" value="false" />
                <c:forEach var="preferencia" items="${usuarioPerfil.preferencias}">
                    <c:if test="${preferencia.tipoPreferencia == 'GUSTO' && preferencia.preferencia == gusto}">
                        <c:set var="checked" value="true" />
                    </c:if>
                </c:forEach>
                <div class="form-check">
                    <input type="checkbox" class="form-check-input" id="gustos_${gusto}" name="preferenciasGustos" value="${gusto}" 
                        <c:if test="${checked == 'true'}">checked="checked"</c:if> 
                        <c:if test="${not isEditable}">disabled</c:if> />
                    <label class="form-check-label" for="gustos_${gusto}">${gusto}</label>
                </div>
            </c:forEach>
        </div>

        <!-- Botón para actualizar el perfil -->
        <button type="submit" class="btn btn-primary" <c:if test="${not isEditable}">disabled</c:if>>Actualizar Perfil</button>
    </form>

    <script src="${pageContext.request.contextPath}/JS/PerfilUsuario.js"></script>
</body>
</html>

