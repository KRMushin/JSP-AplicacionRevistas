<%-- 
    Document   : PerfilUsuario
    Created on : 8 sept 2024, 17:28:05
    Author     : kevin-mushin
--%>

<%@page import="com.mycompany.proyecto_unoipc2.backend.Modelos.FotoUsuario"%>
<%@page import="com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
Usuario usuario = (Usuario) session.getAttribute("usuario");
    FotoUsuario fotoUsuario = usuario.getFoto();
    String fotoBase64 = null;

    if (fotoUsuario != null && fotoUsuario.getFoto() != null && fotoUsuario.getIdFoto() > 0) {
        fotoBase64 = fotoUsuario.getFotoBase64();
    } else {
        fotoBase64 = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAxElEQVQ4T2NkYGBg6AX4nzUw4tILoAEyMxF8hBAohUsGwX3BVEr8D8RRaxEhAEFqMEUBB2hMB+ADUC0zAACJmAdIBNJmCTMgGgrW+IElLKwWgAWIMrjNQCZAApBvYTmCNDMAhGNV1FJiCVZCJUABUzq8AKGYmDpMQYjFejEKsKGYkznoHljoQAKZhsAMzOYBZCc4DWZYD5FEegSBFkJmEIgFTDRBP5rFx5ktwEyVSPDQAAAABJRU5ErkJggg==";
    }
%>
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
           <!-- seccion de foto usuario  -->
           <h2>Perfil de: <strong>${usuario.nombreUsuario}</strong></h2>

            <div class="foto-perfil">
                <c:choose>
                    <c:when test="${usuario.foto != null and usuario.foto.idFoto > 0}">
                        <img src="PerfilUsuarioServlet?action=mostrarImagen&idFoto=${usuario.foto.idFoto}" alt="Foto de Perfil" />
                    </c:when>
                    <c:otherwise>
                        <p>Sin foto disponible</p>
                    </c:otherwise>
                </c:choose>
            </div>
    
       <!-- seccion de datos -->
        <div class="alert alert-info" role="alert">
            Usuario: <strong>${usuario.nombreUsuario}</strong> | Rol: <strong>${usuario.rol} </strong>
            <br></br>
            Nombre: <strong> ${usuario.nombre}</strong>
        </div>
       <!-- seccion de preferencias -->

   <c:set var="ListaPreferencias" value="${usuario.preferencias}"></c:set>

    <!-- fformulario para recoger todas las preferencias -->
    <form action="ruta_a_tu_servlet" method="post" enctype="multipart/form-data">
        <div>
            <!-- bton para habilitar edicion -->
            <button type="button" onclick="mostrarEdicion()">Editar Perfil</button>
            
                <p>Actualizar Foto Perfil </p> 
                <input type="file" name="foto" accept="image/*" required>

            <!-- contenendores de prefrencias -->
            <div id="temas_preferencia">
                <h3>Temas de Preferencia</h3>
                <c:forEach var="preferencia" items="${ListaPreferencias}">
                    <c:if test="${preferencia.tipoPreferencia == 'TEMA_PREFERENCIA'}">
                        <div class="item-preferencia">
                            <input type="checkbox" id="temas_preferencia_${preferencia.nombreUsuario}" name="preferencias" value="${preferencia.preferencia}" checked="checked" />
                            <label for="temas_preferencia_${preferencia.nombreUsuario}">${preferencia.preferencia}</label>
                            <button type="button" class="boton-eliminar oculto" onclick="eliminarPreferencia(this)">Eliminar</button>            
                        </div>
                    </c:if>
                </c:forEach>
            </div>

            <div id="hobbies">
                <h3>Hobbies</h3>
                <c:forEach var="preferencia" items="${ListaPreferencias}">
                    <c:if test="${preferencia.tipoPreferencia == 'HOBBIE'}">
                        <div class="item-preferencia">
                            <input type="checkbox" id="hobbies_${preferencia.nombreUsuario}" name="preferencias" value="${preferencia.preferencia}" checked="checked" />
                            <label for="hobbies_${preferencia.nombreUsuario}">${preferencia.preferencia}</label>
                            <button type="button" class="boton-eliminar oculto" onclick="eliminarPreferencia(this)">Eliminar</button>
                        </div>
                    </c:if>
                </c:forEach>
            </div>

            <div id="gustos">
                <h3>Gustos</h3>
                <c:forEach var="preferencia" items="${ListaPreferencias}">
                    <c:if test="${preferencia.tipoPreferencia == 'GUSTO'}">
                        <div class="item-preferencia">
                            <input type="checkbox" id="gustos_${preferencia.nombreUsuario}" name="preferencias" value="${preferencia.preferencia}" checked="checked" />
                            <label for="gustos_${preferencia.nombreUsuario}">${preferencia.preferencia}</label>
                            <button type="button" class="boton-eliminar oculto" onclick="eliminarPreferencia(this)">Eliminar</button>
                        </div>
                    </c:if>
                </c:forEach>
            </div>

            
            <div class="agregar-preferencia oculto"> 
                <input type="text" id="nuevaPreferencia" placeholder="Nueva preferencia">
                <select id="tipoNuevaPreferencia">
                    <option value="temas_preferencia">Temas de Preferencia</option>
                    <option value="hobbies">Hobbies</option>
                    <option value="gustos">Gustos</option>
                </select>
                <button type="button" onclick="agregarTemaPreferencia()">Añadir Preferencia</button>
            </div>
            
            <button type="submit">  Actualizar Perfil</button>
        </div>
    </form>
</body>
</html>
