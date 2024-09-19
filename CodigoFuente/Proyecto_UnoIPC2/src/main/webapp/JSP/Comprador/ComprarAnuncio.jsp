<%-- 
    Document   : ComprarAnuncio
    Created on : 19 sept 2024, 8:35:01
    Author     : kevin-mushin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comprar Anuncio</title>
                <jsp:include page="/includes/resources.jsp"/>
    </head>
<body>
    <div class="container mt-5">
        <h2 class="text-center mb-4">Formulario de Compra de Anuncio</h2>
        
        <!-- Saldo disponible en cartera digital -->
        <div class="alert alert-info text-center">
            <strong>Saldo en Cartera: Q0.0</strong> <!-- Saldo estático, puedes modificarlo según lo necesites -->
        </div>

        <div class="card">
            <div class="card-body">
                <form action="procesarCompraAnuncio.jsp" method="POST">
                    <div class="form-group">
                        <label for="nombreAnunciante">Nombre del Anunciante</label>
                        <input type="text" class="form-control" id="nombreAnunciante" name="nombreAnunciante" required placeholder="Ingresa el nombre del anunciante">
                    </div>

                    <div class="form-group">
                        <label for="tipoAnuncio">Tipo de Anuncio</label>
                        <select class="form-control" id="tipoAnuncio" name="tipoAnuncio" required>
                            <option value="imagen-texto" data-price="150">Imagen y Texto (Q150)</option>
                            <option value="solo-texto" data-price="100">Solo Texto (Q100)</option>
                            <option value="video" data-price="250">Video (Q250)</option>
                        </select>
                    </div>

                    <!-- Mostrar precio del anuncio -->
                    <div class="form-group">
                        <label for="precioAnuncio">Precio del Anuncio</label>
                        <input type="text" class="form-control" id="precioAnuncio" name="precioAnuncio" readonly>
                    </div>

                    <!-- Campos adicionales para tipos de anuncio -->
                    <div id="camposExtra" class="d-none">
                        <div id="imagenTexto" class="d-none">
                            <div class="form-group">
                                <label for="imagenAnuncio">Subir Imagen</label>
                                <input type="file" class="form-control-file" id="imagenAnuncio" name="imagenAnuncio">
                            </div>
                            <div class="form-group">
                                <label for="textoAnuncio">Texto del Anuncio</label>
                                <textarea class="form-control" id="textoAnuncio" name="textoAnuncio" rows="3"></textarea>
                            </div>
                        </div>

                        <div id="soloTexto" class="d-none">
                            <div class="form-group">
                                <label for="textoSoloAnuncio">Texto del Anuncio</label>
                                <textarea class="form-control" id="textoSoloAnuncio" name="textoSoloAnuncio" rows="3"></textarea>
                            </div>
                        </div>

                        <div id="videoAnuncio" class="d-none">
                            <div class="form-group">
                                <label for="videoAnuncioArchivo">Subir Video</label>
                                <input type="file" class="form-control-file" id="videoAnuncioArchivo" name="videoAnuncioArchivo">
                            </div>
                        </div>
                    </div>

                    <!-- Campo para el tiempo de duración del anuncio -->
                    <div class="form-group mt-3">
                        <label for="tiempoAnuncio">Tiempo de Duración</label>
                        <select class="form-control" id="tiempoAnuncio" name="tiempoAnuncio" required>
                            <option value="1-dia" data-extra="0">1 Día (Sin costo extra)</option>
                            <option value="3-dias" data-extra="50">3 Días (+Q50)</option>
                            <option value="1-semana" data-extra="100">1 Semana (+Q100)</option>
                            <option value="2-semanas" data-extra="150">2 Semanas (+Q150)</option>
                        </select>
                    </div>

                    <!-- Mostrar precio total -->
                    <div class="form-group">
                        <label for="precioTotal">Precio Total</label>
                        <input type="text" class="form-control" id="precioTotal" name="precioTotal" readonly>
                    </div>

                    <!-- Botón para comprar el anuncio -->
                    <button type="submit" class="btn btn-primary btn-block mt-4">Comprar Anuncio</button>
                </form>

                <!-- Botón de regreso -->
                <button class="btn btn-secondary btn-block mt-2" onclick="history.back();">Regresar</button>
            </div>
        </div>
    </div>

    <!-- Enlace a Bootstrap y JQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <!-- Script para manejar la visibilidad de campos extra y mostrar precio -->
    <script>
        $(document).ready(function () {
            function actualizarPrecioTotal() {
                // Obtener precio del tipo de anuncio
                let precioAnuncio = parseInt($('#tipoAnuncio').find(':selected').data('price'));
                // Obtener precio extra por la duración
                let extraDuracion = parseInt($('#tiempoAnuncio').find(':selected').data('extra'));
                // Calcular el precio total
                let precioTotal = precioAnuncio + extraDuracion;
                // Mostrar el precio del anuncio
                $('#precioAnuncio').val('Q' + precioAnuncio);
                // Mostrar el precio total
                $('#precioTotal').val('Q' + precioTotal);
            }

            // Actualizar precio total al cambiar el tipo de anuncio o la duración
            $('#tipoAnuncio, #tiempoAnuncio').on('change', actualizarPrecioTotal);

            // Mostrar precio inicial al cargar la página
            actualizarPrecioTotal();

            // Manejar la visibilidad de campos adicionales
            $('#tipoAnuncio').on('change', function () {
                let tipo = $(this).val();
                $('#camposExtra').removeClass('d-none');
                $('#imagenTexto, #soloTexto, #videoAnuncio').addClass('d-none');

                if (tipo === 'imagen-texto') {
                    $('#imagenTexto').removeClass('d-none');
                } else if (tipo === 'solo-texto') {
                    $('#soloTexto').removeClass('d-none');
                } else if (tipo === 'video') {
                    $('#videoAnuncio').removeClass('d-none');
                }
            });
        });
    </script>
</body>
</html>
