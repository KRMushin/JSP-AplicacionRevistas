/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.CreadoresModelo;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.SuscripcionInvalidaException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Suscripcion;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author kevin-mushin
 */
public class CreadorSuscripcion {

    public Suscripcion crearYValidarSuscripcion(HttpServletRequest request) throws SuscripcionInvalidaException {
        return validarDatos(request);
    }

    private Suscripcion validarDatos(HttpServletRequest request) throws SuscripcionInvalidaException {
         System.out.println(request.getParameter("nombreSuscriptor") + request.getParameter("idRevista") + request.getParameter("fechaCreacion"));
         try {
             Suscripcion suscripcion = new Suscripcion();
             suscripcion.setSuscriptorUsuario(request.getParameter("nombreSuscriptor"));
            
             Long idRevista = Long.valueOf(request.getParameter("idRevista"));
             suscripcion.setIdRevista(idRevista);
             
              String fecha = request.getParameter("fechaCreacion");
              DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
              suscripcion.setFechaSuscripcion(LocalDate.parse(fecha, formatear));
              
              
              System.out.println(suscripcion.getSuscriptorUsuario() + suscripcion.getFechaSuscripcion() + suscripcion.getIdRevista());
              return suscripcion;
              
         } catch (NumberFormatException | NullPointerException| DateTimeParseException e) {
             
             System.out.println(e.getMessage());
             throw new SuscripcionInvalidaException("Los datos proporcionados para la suscripcion son invalidos");
         }
    }
}
