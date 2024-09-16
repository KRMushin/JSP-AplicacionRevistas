<%-- 
    Document   : OpcionesRevistasRol
    Created on : 13 sept 2024, 11:26:58
    Author     : kevin-mushin
--%>

<%@page import="com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="/includes/resources.jsp"/>
    </head>
    <body>
        
        <br>
         <div class="text-center mt-4">
            <div class="btn-group" role="group" aria-label="Opciones de Revista">
                   <c:if test="${usuario.rol == 'EDITOR'}">
                       <a href="#" class="btn btn-outline-primary" onclick="ejecutarAccion('verRevista')">Ver Revista</a>
                      <a href="#" class="btn btn-outline-secondary" onclick="ejecutarAccion('actualizarRevista')"> Editar Revista </a> <!-- un suscriptor ve los detalles, para un suscriptor es la previsualizacion -->
                   
                      

                   </c:if>
                 <c:if test="${usuario.rol == 'SUSCRIPTOR'}">
                     
                     <a href="#" class="btn btn-outline-secondary"> Like</a>
                    <a href="#" class="btn btn-outline-secondary"> Comentar</a>
                    <a href="#" class="btn btn-outline-secondary"> Detalles Revista</a> <!-- es lo mismo que editar revista solo que restringido -->
                    <a href="#" class="btn btn-outline-secondary"> Mas sobre Autor </a>
                    
                 </c:if>
                
            </div>
         <c:if test="${usuario.rol == 'EDITOR'}">
                      <div class="mt-4 p-3 border rounded">
                            <h5 class="text-center">Instrucciones para el Editor</h5>
                            <p class="text-justify">
                            Como editor, aquí puedes visualizar todas tus publicaciones de revistas. Utiliza las flechas de navegación para recorrer cada una de tus revistas. 
                            Si deseas realizar modificaciones, selecciona la revista correspondiente en el carrusel y haz clic en el botón de opciones para visualiza, o ver todos los detalles de 
                            la publicación. 
                            Asegúrate de revisar todos los detalles antes de guardar los cambios.
                            </p>
                        </div>
            </c:if>
              <c:if test="${usuario.rol == 'SUSCRIPTOR'}">
              <div class="mt-4 p-3 border rounded">
                    <h5 class="text-center">Instrucciones para el Suscriptor</h5>
                            <p class="text-justify">
                                Como suscriptor, puedes explorar todas las revistas disponibles utilizando el carrusel. 
                                Navega entre las publicaciones usando las flechas de navegación para ver los títulos y detalles de cada revista. 
                                Haz clic en una revista para conocer más detalles sobre su contenido, autor y fecha de publicación.
                                Disfruta del contenido exclusivo y mantente actualizado con las últimas publicaciones.
                            </p>
                        </div>

            </c:if>

        </div>
            
        <script>
            var idRevistaActual = ''; 
            var nombreRevista = '';

            $(document).ready(function(){
                
                    actualizarDatosSeleccion();
                
                $('#revistaCarrusel').on('slid.bs.carousel', function(){
                    actualizarDatosSeleccion();
                });
            });
            
            function actualizarDatosSeleccion(){
                   
                idRevistaActual = $('.carousel-item.active').attr('id');
                nombreRevista = $('.carousel-item.active').data('nombre');
                
                console.log("ID de la revista actual: " + idRevistaActual); // Debug: para verificar que se actualice
                console.log("Nombre de la revista actual: " + nombreRevista); // Debug: para verificar que se actualice
                
           }

            function ejecutarAccion(accion){
                if (idRevistaActual !== ' ') {
                    alert("Acción: " + accion + " | ID de Revista: " + idRevistaActual);
                    if (accion === 'verRevista') {
                        
                            var url = 'ArchivosPdfServlet?idRevista=' + idRevistaActual + '&nombreRevista=' + encodeURIComponent(nombreRevista);
                           window.open(url, '_blank');
                    }else if (accion === 'actualizarRevista') {
                        var url = 'EditorRevistaServlet?accion=actualizarRevista&idRevistaActualizar=' + idRevistaActual;
                        window.location.href = url;
                    }
                    } else {
                        alert("Ninguna revista seleccionada.");
                    }
            }
    </script>
    </body>
</html>

