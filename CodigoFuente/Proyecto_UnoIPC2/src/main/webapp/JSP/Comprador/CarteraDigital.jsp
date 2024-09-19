<%-- 
    Document   : CarteraDigital
    Created on : 19 sept 2024, 8:51:37
    Author     : kevin-mushin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                        <jsp:include page="/includes/resources.jsp"/>
    </head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Recargar Dinero en Cartera Digital</h2>

        <!-- Saldo disponible en cartera digital -->
        <div class="alert alert-info text-center">
            <strong>Saldo Actual: Q500</strong> <!-- Saldo actual estático, puede ser dinámico -->
        </div>

        <div class="card">
            <div class="card-body">
                <form action="procesarRecarga.jsp" method="POST">
                    <!-- Campo para ingresar la cantidad a recargar -->
                    <div class="form-group">
                        <label for="montoRecarga">Monto a Recargar (Q)</label>
                        <input type="number" class="form-control" id="montoRecarga" name="montoRecarga" required placeholder="Ingresa la cantidad en quetzales">
                    </div>

                    <!-- Campo oculto para la fecha de recarga -->
                    <input type="hidden" id="fechaRecarga" name="fechaRecarga">

                    <!-- Botón para enviar la recarga -->
                    <button type="submit" class="btn btn-primary btn-block mt-4">Recargar</button>
                </form>

                <!-- Botón de regreso -->
                <button class="btn btn-secondary btn-block mt-2" onclick="history.back();">Regresar</button>
            </div>
        </div>
    </div>

    <!-- Script para capturar la fecha actual de recarga -->
    <script>
        $(document).ready(function() {
            // Obtener la fecha actual y formatearla como YYYY-MM-DD
            let fechaActual = new Date().toISOString().split('T')[0];
            // Asignar la fecha actual al campo oculto fechaRecarga
            $('#fechaRecarga').val(fechaActual);
        });
    </script>
</body>
</html>
