<%-- 
    Document   : footer
    Created on : 12 sept 2024, 10:39:19
    Author     : kevin-mushin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="${pageContext.servletContext.contextPath}/JS/jquery.js"></script> 
<script src="${pageContext.servletContext.contextPath}/JS/bootstrap.bundle.js"></script> 
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script> 

<style>
.checkbox-label {
    display: inline-block;
    background-color: #f0f0f0;
    border: 1px solid #ddd;
    padding: 5px 10px;
    margin: 5px;
    border-radius: 15px;
    cursor: pointer;
    transition: background-color 0.3s, color 0.3s;
}

.checkbox-label input[type="checkbox"] {
    display: none; /* Oculta el checkbox original */
}

.checkbox-label.checked {
    background-color: #007bff;
    color: white;
    border-color: #007bff;
}
</style>
<script>
document.addEventListener('DOMContentLoaded', function () {
    const selectCategoria = document.getElementById('idCategoria');
    const checkboxContainer = document.getElementById('checkboxContainer');
    const etiquetas = Array.from(document.querySelectorAll('#hiddenOptions .etiqueta-opcion')); 

        selectCategoria.addEventListener('change', function () {
        const categoriaSeleccionada = this.value;
        checkboxContainer.innerHTML = '';

        etiquetas.forEach(option => {
            if (option.getAttribute('data-categoria') === categoriaSeleccionada) {
                const checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.name = 'idEtiqueta';
                checkbox.value = option.getAttribute('data-value');
                checkbox.id = 'etiqueta-' + option.getAttribute('data-value');

                const label = document.createElement('label');
                label.className = 'checkbox-label';
                label.htmlFor = checkbox.id;
                label.textContent = option.textContent;

                checkbox.addEventListener('change', function () {
                    label.classList.toggle('checked', checkbox.checked); 
                });

                label.appendChild(checkbox);
                checkboxContainer.appendChild(label); 
            }
        });
    });
});
</script>



