/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
window.onload = function() {
    var actualizacionExitosaElement = document.getElementById("actualizacionExitosa");
    var contextPath = document.getElementById("contextPath").value;

    if (actualizacionExitosaElement && actualizacionExitosaElement.value === 'true') {
        alert("Perfil actualizado exitosamente.");
        window.location.href = contextPath + '/JSP/PaginaPrincipal.jsp'; // Redirige a la página principal usando el contextPath
    }
};




 function agregarTemaPreferencia() {
            const nuevoTextoPreferencia = document.getElementById('nuevaPreferencia').value.trim();
            const tipoNuevaPreferencia = document.getElementById('tipoNuevaPreferencia').value;

            const patronTexto = /^[a-zA-Z\s]+$/;

            if (nuevoTextoPreferencia !== '' && patronTexto.test(nuevoTextoPreferencia)) {
                const nuevaPreferenciaItem = document.createElement('div');
                nuevaPreferenciaItem.className = 'item-preferencia';

                const nuevoCheckbox = document.createElement('input');
                nuevoCheckbox.type = 'checkbox';

                    if (tipoNuevaPreferencia === 'temas_preferencia') {
                nuevoCheckbox.name = 'preferenciasTemas';
                } else if (tipoNuevaPreferencia === 'hobbies') {
                    nuevoCheckbox.name = 'preferenciasHobbies';
                } else if (tipoNuevaPreferencia === 'gustos') {
                    nuevoCheckbox.name = 'preferenciasGustos';
                }
                nuevoCheckbox.value = nuevoTextoPreferencia;
                nuevoCheckbox.id = `pref_${Date.now()}`;

                const nuevaEtiqueta = document.createElement('label');
                nuevaEtiqueta.htmlFor = nuevoCheckbox.id;
                nuevaEtiqueta.textContent = nuevoTextoPreferencia;

                const botonEliminar = document.createElement('button');
                botonEliminar.textContent = 'Eliminar';
                botonEliminar.className = 'boton-eliminar oculto'; // Inicialmente oculto
                botonEliminar.type = 'button';
                botonEliminar.onclick = function() { eliminarPreferencia(botonEliminar); };
                botonEliminar.style.display = 'inline-block'; 

                nuevaPreferenciaItem.appendChild(nuevoCheckbox);
                nuevaPreferenciaItem.appendChild(nuevaEtiqueta);
                nuevaPreferenciaItem.appendChild(botonEliminar);

                document.getElementById(`${tipoNuevaPreferencia}`).appendChild(nuevaPreferenciaItem);

                document.getElementById('nuevaPreferencia').value = '';
            } else {
                alert('Por favor, ingresa solo letras y espacios para la nueva preferencia.');
            }
        }
function mostrarEdicion() {
            // Muestra los elementos para agregar y eliminar preferencias
            document.querySelector('.agregar-preferencia').classList.toggle('oculto');
            const botonesEliminar = document.querySelectorAll('.boton-eliminar');
            botonesEliminar.forEach(boton => {
                boton.classList.toggle('oculto');
            });

}
function nuevoHobbie(){
    
    
}
function nuevoGusto(){
    
    
    
}
function eliminarPreferencia(button) {
    if (confirm('¿Estás seguro de que deseas eliminar esta preferencia?')) {
        button.parentElement.remove();
    }
}

