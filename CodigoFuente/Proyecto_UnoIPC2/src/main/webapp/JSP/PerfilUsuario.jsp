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
</head>
<body>
            <jsp:include page="/includes/resources.jsp"/>

    <!-- Mensaje de éxito/error -->
    <c:if test="${not empty mensaje}">
        <div class="${tipoMensaje == 'success' ? 'alert alert-success' : 'alert alert-danger'}">
            <c:out value="${mensaje}" />
        </div>
    </c:if>

    <button type="button" onclick="history.back()">Regresar</button>

    <!-- Sección de perfil de usuario -->
    <h2>Perfil de: <strong>${usuarioPerfil.nombreUsuario}</strong></h2>
    <div class="foto-perfil">
        <c:choose>
            <c:when test="${usuarioPerfil.foto != null && usuarioPerfil.foto.idFoto > 0}">
                <img src="${pageContext.request.contextPath}/PerfilUsuarioServlet?action=mostrarImagen&idFoto=${usuarioPerfil.foto.idFoto}" alt="Foto de Perfil" />
            </c:when>
            <c:otherwise>
                <p>Sin foto disponible</p>
            </c:otherwise>
        </c:choose>
    </div>

    <div class="alert alert-info" role="alert">
        Usuario: <strong>${usuarioPerfil.nombreUsuario}</strong> | Rol: <strong>${usuarioPerfil.rol}</strong>
    </div>

    <!-- Sección del formulario -->
    <form action="${pageContext.request.contextPath}/PerfilUsuarioServlet" method="post" enctype="multipart/form-data">
        <input type="hidden" name="nombreUsuario" value="${usuarioPerfil.nombreUsuario}" />
        <input type="hidden" name="idFoto" value="${usuarioPerfil.foto.idFoto}" />

        <!-- Foto de perfil -->
        <p>Actualizar Foto Perfil</p> 
        <input type="file" name="foto" accept="image/*" 
               <c:if test="${not isEditable}">disabled</c:if> />

        <!-- Descripción -->
        <p>Descripción</p>
        <textarea name="descripcion" <c:if test="${not isEditable}">readonly</c:if>>
            <c:out value="${usuarioPerfil.descripcion != null ? usuarioPerfil.descripcion : 'Agrega una descripción...'}" />
        </textarea>

        <!-- Nombre de pila -->
        <div>
            <p>Nombre:</p>
            <input type="text" id="nombreUsuarioInput" name="nombre" value="${usuarioPerfil.nombre}" 
                   <c:if test="${not isEditable}">readonly</c:if> />
        </div>

        <!-- Preferencias (Temas de Preferencia) -->
        <div id="temas_preferencia">
            <h3>Temas de Preferencia</h3>
            <c:forEach var="tema" items="${fn:split('Terror,Arte,Ciencia,Tecnologia', ',')}">
                <c:set var="checked" value="false" />
                <!-- Comprobamos si el tema está en las preferencias del usuario -->
                <c:forEach var="preferencia" items="${usuarioPerfil.preferencias}">
                    <c:if test="${preferencia.tipoPreferencia == 'TEMA_PREFERENCIA' && preferencia.preferencia == tema}">
                        <c:set var="checked" value="true" />
                    </c:if>
                </c:forEach>
                
                <input type="checkbox" id="${tema}" name="preferenciasTemas" value="${tema}" 
                       <c:if test="${checked == 'true'}">checked="checked"</c:if> 
                       <c:if test="${not isEditable}">disabled</c:if> />
                <label for="${tema}">${tema}</label>
            </c:forEach>
        </div>

        <!-- Hobbies (Siempre visibles, editables solo si 'isEditable') -->
        <div id="hobbies">
            <h3>Hobbies</h3>
            <c:forEach var="hobby" items="${fn:split('Deportes,Lectura,Música', ',')}">
                <c:set var="checked" value="false" />
                <c:forEach var="preferencia" items="${usuarioPerfil.preferencias}">
                    <c:if test="${preferencia.tipoPreferencia == 'HOBBIE' && preferencia.preferencia == hobby}">
                        <c:set var="checked" value="true" />
                    </c:if>
                </c:forEach>

                <input type="checkbox" id="hobbies_${hobby}" name="preferenciasHobbies" value="${hobby}" 
                       <c:if test="${checked == 'true'}">checked="checked"</c:if> 
                       <c:if test="${not isEditable}">disabled</c:if> />
                <label for="hobbies_${hobby}">${hobby}</label>
            </c:forEach>
        </div>

        <!-- Gustos (Siempre visibles, editables solo si 'isEditable') -->
        <div id="gustos">
            <h3>Gustos</h3>
            <c:forEach var="gusto" items="${fn:split('Cine,Viajar,Cocina', ',')}">
                <c:set var="checked" value="false" />
                <c:forEach var="preferencia" items="${usuarioPerfil.preferencias}">
                    <c:if test="${preferencia.tipoPreferencia == 'GUSTO' && preferencia.preferencia == gusto}">
                        <c:set var="checked" value="true" />
                    </c:if>
                </c:forEach>

                <input type="checkbox" id="gustos_${gusto}" name="preferenciasGustos" value="${gusto}" 
                       <c:if test="${checked == 'true'}">checked="checked"</c:if> 
                       <c:if test="${not isEditable}">disabled</c:if> />
                <label for="gustos_${gusto}">${gusto}</label>
            </c:forEach>
        </div>

        <!-- Botón para actualizar el perfil (solo habilitado si se puede editar) -->
        <button type="submit" <c:if test="${not isEditable}">disabled</c:if>>Actualizar Perfil</button>
    </form>

    <script src="${pageContext.request.contextPath}/JS/PerfilUsuario.js"></script>
</body>
</html>

