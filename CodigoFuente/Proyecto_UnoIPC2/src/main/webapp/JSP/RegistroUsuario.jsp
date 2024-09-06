<%-- 
    Document   : RegistroUsuario
    Created on : 4 sept 2024, 22:24:35
    Author     : kevin-mushin
--%>

<%@page import="java.util.Map"%>
<%@page import="com.mycompany.proyecto_unoipc2.Utileria.RespuestaServicio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Formulario de Registro</title>
            <link rel="stylesheet" href="../CSS/Styles.css">
    </head>
    <body>
         <!-- Scriptlet para obtener errores del formulario -->
    <%
        RespuestaServicio erroresFormulario = (RespuestaServicio) request.getAttribute("ERRORES_FORMULARIO");
        RespuestaServicio erroresServicio = (RespuestaServicio) request.getAttribute("ERRORES_SERVICIO");
    %>

    <!-- Mostrar errores del formulario si existen -->
    <% if (erroresFormulario != null && !erroresFormulario.getErrores().isEmpty()) { %>
        <div class="error">
            <h3>Errores en el Formulario:</h3>
            <ul>
                <% 
                    for (Map.Entry<String, String> entry : erroresFormulario.getErrores().entrySet()) { 
                        String clave = entry.getKey();
                        String mensajeError = entry.getValue();
                %>
                    <li><%= clave %>: <%= mensajeError %></li>
                <% 
                    } 
                %>
            </ul>
        </div>
    <% } %>

    <!-- Mostrar errores del servicio si existen -->
    <% if (erroresServicio != null && !erroresServicio.getErrores().isEmpty()) { %>
        <div class="error">
            <h3>Errores de Registro:</h3>
            <ul>
                <% 
                    for (Map.Entry<String, String> entry : erroresServicio.getErrores().entrySet()) { 
                        String clave = entry.getKey();
                        String mensajeError = entry.getValue();
                %>
                    <li><%= clave %>: <%= mensajeError %></li>
                <% 
                    } 
                %>
            </ul>
        </div>
    <% } %>

        
        <h2 class="text-center">Registro de Usuario - Revistas El Mosquito</h2>
    <p class="text-center">Regístrate para disfrutar de una experiencia personalizada en nuestra aplicación de revistas. Al crear tu cuenta, podrás acceder a contenido exclusivo</p>

    <div class="main-container">
        <div class="container">
            <form action="${pageContext.request.contextPath}/RegistroServlet" method="post" enctype="multipart/form-data">
                
                <label for="nombreUsuario">Nombre de Usuario:</label>
                <input type="text" id="nombreUsuario" name="nombreUsuario" required>
                <br><br>

                <label for="password">Contraseña:</label>
                <input type="password" id="password" name="password" required>
                <br><br>

                <label for="nombrePila">Nombre de Pila:</label>
                <input type="text" id="nombrePila" name="nombrePila" required>
                <br><br>

                
                <p>Seleccione el tipo de usuario:</p>
                <input type="radio" id="suscriptor" name="rolEscogido" value="SUSCRIPTOR" required>
                <label for="suscriptor">Suscriptor</label><br>

                <input type="radio" id="comprador" name="rolEscogido" value="COMPRADOR" required>
                <label for="comprador">Comprador</label><br>

                <input type="radio" id="editor" name="rolEscogido" value="EDITOR" required>
                <label for="editor">Editor</label><br><br>

                <button type="button" onclick="toggleOptionalSection()">Personaliza tu perfil (opcional)</button>
                
                <button type="submit">Registrarse</button>
            </form>
            


            <div id="optional-section">
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

                <label for="foto">Sube tu foto para tu perfil (PNG, JPG):</label>
                <input type="file" id="foto" name="foto" accept=".png, .jpg, .jpeg">
                <br><br>
            </div>
        </div>
    </div>

    <script src="../JS/Script.js"></script>
    <a href="../index.jsp" class=" main-container">Volver</a>
    </body>
</html>
