/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_unoipc2.backend.CreadoresModelo;

import com.mycompany.proyecto_unoipc2.backend.Excepciones.ComentarioInvalidoException;
import com.mycompany.proyecto_unoipc2.backend.Modelos.Comentario;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author kevin-mushin
 */
public class CreadorComentario {

    public Comentario validarYCrearComentario(HttpServletRequest request) throws ComentarioInvalidoException{
        Comentario comentario = new Comentario();
        
        try {
                comentario.setComentario(request.getParameter("comentario"));
                comentario.setNombreUsuario(request.getParameter("nombreUsuario"));
                System.out.println(request.getParameter("comentario"));
                Long idRevista = Long.valueOf(request.getParameter("idRevista"));
                comentario.setIdRevista(idRevista);

                String fecha = request.getParameter("fechaComentario");
                DateTimeFormatter formatear = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaCreacion = LocalDate.parse(fecha, formatear);
                comentario.setFechaComentario(fechaCreacion);
                System.out.println(comentario.toString());
                
        } catch (NumberFormatException | NullPointerException | DateTimeParseException e) {
            System.out.println(e);
            throw new ComentarioInvalidoException(" Los datos proporcionados para el comentario son invalidos, porfavor verifique nuevamente sus datos");
        }
        
        return comentario;
    }
    
}
