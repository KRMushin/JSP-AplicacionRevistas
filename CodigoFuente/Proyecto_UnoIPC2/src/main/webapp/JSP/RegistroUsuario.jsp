<%-- 
    Document   : RegistroUsuario
    Created on : 4 sept 2024, 22:24:35
    Author     : kevin-mushin
--%>

<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Formulario de Registro</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/CSS/Styles.css">
            <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css" rel="stylesheet">
            <script src="${pageContext.request.contextPath}/JS/Script.js"></script>

    </head>
    <body>
        
        <h2 class="text-center">Registro de Usuario - Revistas El Mosquito</h2>
    <p class="text-center">Regístrate para disfrutar de una experiencia personalizada en nuestra aplicación de revistas. Al crear tu cuenta, podrás acceder a contenido exclusivo</p>
     <%
            String error = (String) request.getAttribute("Error_datos_invalidos");
            if (error != null) {
        %>
            <div class="mensaje-error">
                <p><strong>Error:</strong> <%= error %></p>
            </div>
        <%
            }
        %>
    <div class="main-container">
        <div class="container">
            <form action="${pageContext.request.contextPath}/RegistroServlet" method="post" enctype="multipart/form-data">
                
                <label for="nombreUsuario">Nombre de Usuario:
                    <span class="informacion" title="Debe ser menor a 15 caracteres y sin espacios">
                                <i class="bi bi-info-circle"></i>
                    </span>
                </label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" required>
                <br><br>

                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
                <br><br>

                <label for="nombrePila">Nombre Completo:
                     <span class="informacion" title=" Nombre completo sin espacios">
                                <i class="bi bi-info-circle"></i>
                    </span>
                </label>
                <input type="text" id="nombrePila" name="nombrePila" required>
                
                <p>Seleccione el tipo de usuario:</p>
                <input type="radio" id="suscriptor" name="rolEscogido" value="SUSCRIPTOR" required>
                <label for="suscriptor">Suscriptor</label><br>

                <input type="radio" id="comprador" name="rolEscogido" value="COMPRADOR" required>
                <label for="comprador">Comprador</label><br>

                <input type="radio" id="editor" name="rolEscogido" value="EDITOR" required>
                <label for="editor">Editor</label><br><br>

                <div id="seccion-dinamica">
                <h3>Información Adicional</h3>
                
                <p>Cuéntanos más sobre tus intereses...:</p>

                <label>Selecciona tus temas de preferencia:</label><br>
                <input type="checkbox" id="tecnologia" name="preferencias" value="tecnologia">
                <label for="tecnologia">Tecnología</label><br>

                <input type="checkbox" id="ciencia" name="preferencias" value="ciencia">
                <label for="ciencia">Ciencia</label><br>

                <input type="checkbox" id="arte" name="preferencias" value="arte">
                <label for="arte">Arte</label><br>

                <input type="checkbox" id="deportes" name="preferencias" value="deportes">
                <label for="deportes">Deportes</label><br>

                <input type="checkbox" id="musica" name="preferencias" value="musica">
                <label for="musica">Música</label><br><br>

            </div>
                <button type="button" onclick="ocultarSeccion()">Personaliza tu perfil (opcional)</button>
                
                <button type="submit">Registrarse</button>
            
                
                
            </form>
        </div>
    </div>

    <a href="../index.jsp" class=" main-container"> Volver Inicio</a>
    </body>
</html>