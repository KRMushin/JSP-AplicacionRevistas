<%-- 
    Document   : RegistroExitoso
    Created on : 5 sept 2024, 22:01:21
    Author     : kevin-mushin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="Styles.css">
                    <jsp:include page="/includes/resources.jsp"/>
    </head>
<body>
    <div class="container mt-5">
        <div class="card text-center shadow p-4">
            <div class="card-body">
                <h1 class="display-4 text-success">Registro Exitoso</h1>
                <p class="lead">Muchas gracias por formar parte de esta comunidad de revistas.</p>
                <p class="font-weight-bold">Bienvenido, <strong>${nombrePila}</strong></p>
                <a href="../index.jsp" class="btn btn-primary btn-lg mt-3">Volver</a>
                <p class="mt-4">Puedes ingresar en la sección de login con el nombre de usuario: <strong>${nombreUsuario}</strong> y la contraseña que proporcionaste.</p>
            </div>
        </div>
    </div>

</body>
</html>
