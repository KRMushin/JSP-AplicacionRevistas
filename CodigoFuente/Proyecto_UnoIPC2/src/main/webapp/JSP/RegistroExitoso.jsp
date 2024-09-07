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
    </head>
    <body>
        <div class="text-center">
            <h1>        REGISTRO EXITOSO </h1>
            
            <p> Muchas gracias por formar parte de esta comunidad de revistas</p>
            <p><Strong>${nombrePila} </strong>></p>
            <a href="index.html">volver</a>
            <p> Puedes ingresar en la seccion de login con el nombre de usuario:<strong>${nombreUsuario}</strong>> y la contraseña que proporcionaste</p>
        </div>
    </body>
</html>
