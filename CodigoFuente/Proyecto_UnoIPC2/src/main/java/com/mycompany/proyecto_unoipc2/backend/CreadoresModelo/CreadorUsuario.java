/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.CreadoresModelo;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.DatosInvalidosRegistro;
import com.mycompany.proyecto_unoipc2.backend.Modelos.PreferenciaUsuario;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Usuario;
import com.mycompany.proyecto_unoipc2.backend.Utileria.Encriptador;
import com.mycompany.proyecto_unoipc2.backend.Utileria.Rol;
import com.mycompany.proyecto_unoipc2.backend.Utileria.TipoPreferencia;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class CreadorUsuario {
    
    Encriptador encriptador = new Encriptador();

    public Usuario crearUsuario(HttpServletRequest req) throws DatosInvalidosRegistro{
        Usuario nuevoUsuario = extraerYValidar(req);
        nuevoUsuario.setPassword(encriptador.encryptPassword(nuevoUsuario.getPassword()));
        
        manejarCamposOpcionales(req, nuevoUsuario);
        
        if (!nuevoUsuario.esValido()) {
           throw new DatosInvalidosRegistro("Los datos de caracter obligatorio no fueron introducidos o se ingresaron de manera incorrecta");
        }
        return nuevoUsuario;
    }

    private Usuario extraerYValidar(HttpServletRequest req) throws DatosInvalidosRegistro{
            Usuario usuario = new Usuario();
            try {
                   usuario.setNombreUsuario(req.getParameter("nombreUsuario"));
                   usuario.setPassword(req.getParameter("password"));
                   usuario.setRol(Rol.valueOf(req.getParameter("rolEscogido")));
                   usuario.setNombre(req.getParameter("nombrePila"));
                    return usuario;
                }catch (IllegalArgumentException | NullPointerException e) {
                     throw new DatosInvalidosRegistro("  Error en los datos obligatorios");
                }
    }

    private void manejarCamposOpcionales(HttpServletRequest req, Usuario nuevoUsuario) throws DatosInvalidosRegistro {
    try {
        String[] preferencias = req.getParameterValues("preferencias");
        if (preferencias != null && preferencias.length > 0) { 
            List<PreferenciaUsuario> pref = crearPreferencias(preferencias, TipoPreferencia.TEMA_PREFERENCIA, nuevoUsuario.getNombreUsuario());
            nuevoUsuario.setPreferencias(pref);
        }
    } catch (DatosInvalidosRegistro e) {
        throw new DatosInvalidosRegistro(" Los campos opcionales no contienen el formato indicado ");
    } 
}

    private List<PreferenciaUsuario> crearPreferencias(String[] preferencias, TipoPreferencia tipoPreferencia, String nombreUsuario) throws DatosInvalidosRegistro{
    List<PreferenciaUsuario> pref = new ArrayList<>();
    
    // verifica que sean solo letras
    String regex = "^[a-zA-Z]+$"; 

    for (String preferencia : preferencias) {
        if (preferencia != null) {
            // verificar que la preferencia contenga solo letras y no tenga espacios
            if (preferencia.matches(regex)) {
                pref.add(new PreferenciaUsuario(preferencia, nombreUsuario, tipoPreferencia));
            } else {
                throw new DatosInvalidosRegistro("La preferencia '" + preferencia + "' es inválida: debe contener solo letras sin espacios.");
            }
        }
    }

    return pref; 
}

    
}
