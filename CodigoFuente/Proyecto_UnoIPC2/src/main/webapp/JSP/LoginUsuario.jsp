<%-- 
    Document   : LoginUsuario
    Created on : 4 sept 2024, 22:24:47
    Author     : kevin-mushin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Login</title>
    <!-- Importar CSS de Bootstrap -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    
    <div class="container">
        <div class="row justify-content-center mt-5">
            <div class="col-md-4">
                <h3 class="text-center">Iniciar Sesión</h3>
                
                    <% 
                    String error = (String) request.getAttribute("errorDB");
                    String errorUsuario = (String) request.getAttribute("errorLogin");

                    if (error != null && !error.isEmpty()) { 
                %>
                    <div class="alert alert-danger text-center mt-3">
                        <strong>Error:</strong> <%= error %>
                    </div>
                <% 
                    } else if (errorUsuario != null && !errorUsuario.isEmpty()) { 
                %>
                    <div class="alert alert-danger text-center mt-3">
                        <strong>Error:</strong> <%= errorUsuario %>
                    </div>
                <% 
                    } 
                %>
                            
                <form method="post" action="${pageContext.request.contextPath}/LoginServlet">
                    <div class="form-group">
                        <label for="nombreUsuario">Nombre de Usuario</label>
                        <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario" placeholder="Ingresa tu usuario" required>
                    </div>
                    <div class="form-group">
                        <label for="password">Contraseña</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Ingresa tu contraseña" required>
                    </div>
                    <button type="submit" class="btn btn-primary btn-block">Iniciar Sesión</button>
                    <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-secondary btn-block mt-2">Volver</a>
              
                </form>
            </div>
        </div>
    </div>

    <!-- funcionaludades para diseño de bootstrap  -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
