<%-- 
    Document   : AñadirEtiquetas
    Created on : 12 sept 2024, 0:22:29
    Author     : kevin-mushin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    </div> 
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/select2.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const categoriaSelect = document.getElementById('idCategoria');
            const etiquetaSelect = document.getElementById('idEtiqueta');

            if (window.jQuery) {
                $('#idCategoria').select2({
                    placeholder: "Seleccione una categoría",
                    allowClear: true,
                    width: '100%'
                });

                $('#idEtiqueta').select2({
                    placeholder: "Seleccione múltiples etiquetas",
                    allowClear: true,
                    width: '100%'
                });
            }
        });
    </script>
</body>
</html>
